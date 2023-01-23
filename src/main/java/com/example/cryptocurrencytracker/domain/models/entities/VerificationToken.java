package com.example.cryptocurrencytracker.domain.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String token;

    @OneToOne(mappedBy="verificationToken")
    private UserAccount userAccount;

    private LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(10l);

    public VerificationToken(String token, UserAccount userAccount) {
        this.token = token;
        this.userAccount = userAccount;
    }

}
