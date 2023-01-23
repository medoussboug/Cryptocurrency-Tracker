package com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class FavoriteCryptocurrencyExistsException extends GeneralException {
    public FavoriteCryptocurrencyExistsException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "This Favorite cryptocurrency already exists for this user"
        );
    }
}
