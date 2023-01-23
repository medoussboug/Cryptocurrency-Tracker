package com.example.cryptocurrencytracker.domain.models.entities;

import com.example.cryptocurrencytracker.domain.models.UserAccountRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@Table(name = "User_account", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Size(min = 6, max = 15, message = "username should be more than 6 caracters and less than 15")
    private String username;
    private String password;
    @Email(message = "Provide Valid Email")
    private String email;
    private String fullName;
    private String title;
    private String organization;
    private LocalDate dateOfBirth;
    private LocalDate dateOfCreation;
    @Lob
    private byte[] picture;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserAccountRole userRole = UserAccountRole.UNVERIFIED_USER;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "verificationToken_id", referencedColumnName = "id")
    private VerificationToken verificationToken;

    public UserAccountRole getUserRole() {
        return userRole;
    }
}
