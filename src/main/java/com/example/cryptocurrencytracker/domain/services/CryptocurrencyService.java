package com.example.cryptocurrencytracker.domain.services;

import com.example.cryptocurrencytracker.data.mappers.CryptocurrencyMapper;
import com.example.cryptocurrencytracker.data.repositories.CryptocurrencyRepository;
import com.example.cryptocurrencytracker.data.repositories.FavoriteCryptocurrencyRepository;
import com.example.cryptocurrencytracker.domain.exceptions.cryptocurrency.CryptocurrencyNotFoundException;
import com.example.cryptocurrencytracker.domain.models.NotificationSubjects;
import com.example.cryptocurrencytracker.domain.models.dtos.CryptocurrencyDTO;
import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import com.example.cryptocurrencytracker.domain.models.entities.FavoriteCryptocurrency;
import com.example.cryptocurrencytracker.presentation.CoinGeckoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CryptocurrencyService {

    private final NotificationService notificationService;
    private final CryptocurrencyMapper cryptocurrencyMapper;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final FavoriteCryptocurrencyRepository favoriteCryptocurrencyRepository;
    private final CoinGeckoClient coinGeckoClient;

    @Transactional
    public void loadData() {
        List<CryptocurrencyDTO> loadedCryptocurrencies = coinGeckoClient.getCryptocurrencies();
        for (CryptocurrencyDTO loadedCryptocurrency : loadedCryptocurrencies) {
            Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(loadedCryptocurrency.id)
                    .orElseThrow(CryptocurrencyNotFoundException::new);
            if (!loadedCryptocurrency.currentPrice.equals(cryptocurrency.getCurrentPrice())) {
                Set<FavoriteCryptocurrency> favoriteCryptocurrencies = cryptocurrency.getUsersFavoriteCryptocurrencies();
                if (favoriteCryptocurrencies != null && favoriteCryptocurrencies.size() > 0) {
                    for (FavoriteCryptocurrency favoriteCryptocurrency : favoriteCryptocurrencies) {
                        if ((loadedCryptocurrency.currentPrice <= favoriteCryptocurrency.getDesiredBuyingPrice()
                                || loadedCryptocurrency.currentPrice >= favoriteCryptocurrency.getDesiredSellingPrice())
                                && !favoriteCryptocurrency.getNotified()) {
                            notificationService.sendEmail(
                                    favoriteCryptocurrency.getUser().getEmail(),
                                    NotificationSubjects.DESIRED_PRICE_REACHED.name(),
                                    notificationService.priceReachedNotificationBodyFormatter(
                                            favoriteCryptocurrency.getUser().getUsername(),
                                            favoriteCryptocurrency.getCryptoName()
                                    )
                            );
                            favoriteCryptocurrency.setNotified(true);
                        }
                        favoriteCryptocurrency.setCryptoPrice(loadedCryptocurrency.currentPrice);
                        favoriteCryptocurrencyRepository.save(favoriteCryptocurrency);
                    }
                }
            }
            cryptocurrencyRepository.save(cryptocurrencyMapper.mapToEntity(loadedCryptocurrency));
        }
    }

    public List<Cryptocurrency> listData() {
        return cryptocurrencyRepository.findAll();
    }
}
