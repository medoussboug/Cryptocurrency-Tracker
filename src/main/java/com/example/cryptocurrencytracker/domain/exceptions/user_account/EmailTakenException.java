package com.example.cryptocurrencytracker.domain.exceptions.user_account;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class EmailTakenException extends GeneralException {

    public EmailTakenException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "Email is already taken"
        );
    }
}
