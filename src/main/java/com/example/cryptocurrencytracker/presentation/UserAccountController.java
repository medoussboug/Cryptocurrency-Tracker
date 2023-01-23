package com.example.cryptocurrencytracker.presentation;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.example.cryptocurrencytracker.domain.models.dtos.UserAccountDTO;
import com.example.cryptocurrencytracker.domain.services.UserAccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/add")
    public ResponseEntity<UserAccountDTO> addUser(@RequestBody @Valid UserAccountDTO userAccountDTO) {
        return ResponseEntity.ok(userAccountService.createUser(userAccountDTO));
    }

    @GetMapping("/actual")
    public ResponseEntity<UserAccountDTO> getUser(Principal principal) {
        return ResponseEntity.ok(userAccountService.getUserAccount(principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<UserAccountDTO>> getAllUsers() {
        return ResponseEntity.ok(userAccountService.getAllUsers());
    }

    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadUserPicture(@RequestParam("image") MultipartFile picture, Principal principal) throws IOException {
        userAccountService.uploadProfilePicture(picture, principal.getName());
        return ResponseEntity.ok("Donnne");
    }

    @GetMapping(value = "/picture", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<?> getUserPicture(Principal principal) throws IOException {
        return ResponseEntity.ok(userAccountService.viewProfilePicture(principal.getName()));
    }

    @PutMapping("/fullname/{fullname}")
    public ResponseEntity<UserAccountDTO> updateUserFullname(Principal principal, @PathVariable String fullname) {
        return ResponseEntity.ok(userAccountService.updateFullName(principal.getName(), fullname));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<UserAccountDTO> updateUserEmail(Principal principal, @PathVariable String email) {
        return ResponseEntity.ok(userAccountService.updateEmail(principal.getName(), email));
    }

    @PutMapping("/password/{password}")
    public ResponseEntity<?> updateUserPassword(Principal principal, @PathVariable String password) {
        userAccountService.updatePassword(principal.getName(), password);
        return ResponseEntity.ok("Password Updated Successfully");
    }

    @PutMapping("/title/{title}")
    public ResponseEntity<UserAccountDTO> updateUserTitle(Principal principal, @PathVariable String title) {
        return ResponseEntity.ok(userAccountService.updateTitle(principal.getName(), title));
    }

    @PutMapping("/organization/{organization}")
    public ResponseEntity<UserAccountDTO> updateUserOrganization(Principal principal, @PathVariable String organization) {
        return ResponseEntity.ok(userAccountService.updateOrganization(principal.getName(), organization));
    }

    @PostMapping("/verification/{token}")
    @Secured("UNVERIFIED_USER")
    Boolean verifyAccount(String token, Principal principal) {
        log.info("Verify TOken");
        return userAccountService.verifyAccount(principal.getName(), token);
    }
}

