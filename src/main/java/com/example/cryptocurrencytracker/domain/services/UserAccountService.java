package com.example.cryptocurrencytracker.domain.services;

import com.example.cryptocurrencytracker.data.mappers.UserAccountMapper;
import com.example.cryptocurrencytracker.data.repositories.UserAccountRepository;
import com.example.cryptocurrencytracker.data.repositories.VerificationTokenRepository;
import com.example.cryptocurrencytracker.domain.exceptions.user_account.EmailTakenException;
import com.example.cryptocurrencytracker.domain.exceptions.user_account.UserNotFoundException;
import com.example.cryptocurrencytracker.domain.exceptions.user_account.UsernameTakenException;
import com.example.cryptocurrencytracker.domain.models.NotificationSubjects;
import com.example.cryptocurrencytracker.domain.models.UserAccountRole;
import com.example.cryptocurrencytracker.domain.models.dtos.UserAccountDTO;
import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import com.example.cryptocurrencytracker.domain.models.entities.VerificationToken;
import com.example.cryptocurrencytracker.security.MyUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountService {
    private final UserAccountRepository userRepository;
    private final NotificationService notificationService;
    private final UserAccountMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    public UserAccountDTO createUser(UserAccountDTO userDTO) {
        userRepository.findByUsername(userDTO.username())
                .ifPresent(user -> {
                    throw new UsernameTakenException();
                });
        userRepository.findByEmail(userDTO.email())
                .ifPresent((user) -> {
                    throw new EmailTakenException();
                });
        UserAccount newUser = userMapper.toEntity(userDTO);
        newUser.setDateOfCreation(LocalDate.now());
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));
        newUser.setUserRole(UserAccountRole.UNVERIFIED_USER);
        UserAccountDTO user = userMapper.toDTO(userRepository.save(newUser));
        sendVerificationEmail(newUser);
        return user;
    }

    private void sendVerificationEmail(UserAccount newAccount) {
        String token = String.format("%06d", (int) (Math.random() * 1000000));
        VerificationToken verificationToken = new VerificationToken(token, newAccount);
        verificationTokenRepository.save(verificationToken);

        log.info("Validation token generated");
        //send confirmation email
        notificationService.sendEmail(newAccount.getEmail(), NotificationSubjects.USER_CREATED.name(), notificationService.welcomeNotificationBodyFormatter(newAccount.getUsername(), verificationToken.getToken(), verificationToken.getExpiryDate().toString()));

        log.info("Email message sent to notification service");
    }

    @Transactional
    public Boolean verifyAccount(String username, String token) {
        final var isVerifiedWrapper = new Object() {
            Boolean isVerified;
        };
        UserAccount userAccount = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
        verificationTokenRepository.findByUserAccountAndToken(userAccount, token).ifPresentOrElse((VerificationToken) -> {
                    userAccount.setUserRole(UserAccountRole.VERIFIED_USER);
                    isVerifiedWrapper.isVerified = true;
                    log.info("Account now certified");
                }
                , () -> {
                    sendVerificationEmail(userAccount);
                    isVerifiedWrapper.isVerified = false;
                    log.info("Email message sent to notification service");
                });

        return isVerifiedWrapper.isVerified;
    }

    public UserAccountDTO getUserAccount(String usernameOrEmail) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserAccountDTO updateFullName(String usernameOrEmail, String fullname) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        user.setFullName(fullname);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserAccountDTO updateEmail(String usernameOrEmail, String email) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        user.setEmail(email);
        return userMapper.toDTO(user);
    }

    @Transactional
    public void updatePassword(String usernameOrEmail, String password) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        user.setPassword(passwordEncoder.encode(password));
    }

    @Transactional
    public UserAccountDTO updateTitle(String usernameOrEmail, String title) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        user.setTitle(title);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserAccountDTO updateOrganization(String usernameOrEmail, String organization) {
        UserAccount user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException());
        user.setOrganization(organization);
        return userMapper.toDTO(user);
    }

    @Transactional
    public void uploadProfilePicture(MultipartFile picture, String username) throws IOException {
        UserAccount user = userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new UserNotFoundException());
        user.setPicture(picture.getBytes());
    }

    public byte[] viewProfilePicture(String username) throws IOException {
        UserAccount user = userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new UserNotFoundException());
        byte[] picture;
        if (user.getPicture() != null) {
            picture = user.getPicture();
        } else {
            picture = new byte[5];
        }
        return picture;
    }

    public List<UserAccountDTO> getAllUsers() {
        List<UserAccount> userAccounts = userRepository.findAll();
        return userMapper.toDTO(userAccounts);
    }

    public UserDetails loadUserByUsername(String username) {
        UserAccount user = userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new UserNotFoundException());
        return new MyUserDetails(userMapper.toDTO(user));
    }
}

