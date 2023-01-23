package com.example.cryptocurrencytracker.presentation;

import com.example.cryptocurrencytracker.domain.models.dtos.UserAccountDTO;
import com.example.cryptocurrencytracker.domain.services.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600)
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

    @PostMapping("/verification")
    Boolean verifyAccount(@RequestParam("image") String token, Principal principal) {
        log.info("Verify Token");
        return userAccountService.verifyAccount(principal.getName(), token);
    }
}

