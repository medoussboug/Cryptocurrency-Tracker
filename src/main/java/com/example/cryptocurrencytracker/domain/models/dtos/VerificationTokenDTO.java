package com.example.cryptocurrencytracker.domain.models.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public record VerificationTokenDTO(
        String username,
        String email,
        String token,
        LocalDateTime expiryDate
) implements Serializable {

}
