package com.example.cryptocurrencytracker.domain.exceptions.user_account;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;


public class UserNotFoundException extends GeneralException {

    public UserNotFoundException() {
        super(
                HttpStatus.FORBIDDEN.toString(),
                "email or username doesn't exist, user not found in the database"
        );
    }

}
