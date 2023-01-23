package com.example.cryptocurrencytracker.domain.exceptions.user_account;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class UsernameTakenException extends GeneralException {

    public UsernameTakenException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "Username is already taken"
        );
    }
}
