package com.example.cryptocurrencytracker.domain.exceptions;

import org.springframework.http.HttpStatus;


public class UserNotFoundException extends GeneralException {

    public UserNotFoundException() {
        super(
                HttpStatus.FORBIDDEN.toString(),
                "email or username doesn't exist, user not found in the database"
        );
    }

}
