package com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency;

import com.example.cryptocurrencytracker.domain.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class DesiredPriceMismatchException extends GeneralException {

    public DesiredPriceMismatchException() {
        super(
                HttpStatus.BAD_REQUEST.toString(),
                "Desired price mismatch, either the desired selling price is lower than the actual price," +
                        " or the desired buying price is higher than actual price"
        );
    }
}
