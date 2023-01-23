package com.example.cryptocurrencytracker.data.mappers;

import com.example.cryptocurrencytracker.data.repositories.CryptocurrencyRepository;
import com.example.cryptocurrencytracker.domain.models.dtos.FavoriteCryptocrurrencyDTO;
import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import com.example.cryptocurrencytracker.domain.models.entities.FavoriteCryptocurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FavoriteCryptocurrencyMapper {

    private final CryptocurrencyRepository cryptocurrencyRepository;

    public FavoriteCryptocurrency mapToEntity(FavoriteCryptocrurrencyDTO favoriteCryptocrurrencyDTO) {
        Optional<Cryptocurrency> cryptocurrency = cryptocurrencyRepository.findById(favoriteCryptocrurrencyDTO.cryptoId);
        return cryptocurrency.map(value -> FavoriteCryptocurrency.builder()
                        .cryptoName(value.getId())
                        .cryptoPrice(value.getCurrentPrice())
                        .desiredBuyingPrice(favoriteCryptocrurrencyDTO.desiredBuyingPrice)
                        .desiredSellingPrice(favoriteCryptocrurrencyDTO.desiredSellingPrice)
                        .build())
                .orElse(null);
    }
}
