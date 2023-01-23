package com.example.cryptocurrencytracker.security;

public record AuthenticationResponse(
        String username,
        String tokenType,
        String token
) {

}

