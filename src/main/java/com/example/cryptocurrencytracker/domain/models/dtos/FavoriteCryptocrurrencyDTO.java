package com.example.cryptocurrencytracker.domain.models.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FavoriteCryptocrurrencyDTO {
    public final String cryptoId;
    public final Double desiredSellingPrice;
    public final Double desiredBuyingPrice;
}
