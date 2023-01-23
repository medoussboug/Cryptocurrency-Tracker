package com.example.cryptocurrencytracker.domain.exceptions.cryptocurrency;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class CryptocurrencyNotFoundException extends GeneralException {

    public CryptocurrencyNotFoundException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "The Cryptocurrency You are Looking For Doesn't exist"
        );
    }
}
