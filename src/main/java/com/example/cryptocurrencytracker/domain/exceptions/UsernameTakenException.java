package com.example.cryptocurrencytracker.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameTakenException extends GeneralException {

    public UsernameTakenException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "Username is already taken"
        );
    }
}
