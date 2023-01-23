package com.example.cryptocurrencytracker.domain.exceptions;

import org.springframework.http.HttpStatus;

public class EmailTakenException extends GeneralException {

    public EmailTakenException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "Email is already taken"
        );
    }
}
