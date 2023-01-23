package com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class FavoriteCryptocurrencyNotFoundException extends GeneralException {

    public FavoriteCryptocurrencyNotFoundException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "The favorite cryptocurrency You are Looking For Doesn't exist"
        );
    }
}
