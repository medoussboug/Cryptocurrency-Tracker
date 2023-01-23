package com.example.cryptocurrencytracker.security;

public record AuthenticationRequest(
        String username,
        String password
) {

}
