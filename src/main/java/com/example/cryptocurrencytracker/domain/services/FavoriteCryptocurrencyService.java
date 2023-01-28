package com.example.cryptocurrencytracker.domain.services;

import com.example.cryptocurrencytracker.data.mappers.FavoriteCryptocurrencyMapper;
import com.example.cryptocurrencytracker.data.repositories.CryptocurrencyRepository;
import com.example.cryptocurrencytracker.data.repositories.FavoriteCryptocurrencyRepository;
import com.example.cryptocurrencytracker.data.repositories.UserAccountRepository;
import com.example.cryptocurrencytracker.domain.exceptions.cryptocurrency.CryptocurrencyNotFoundException;
import com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency.DesiredPriceMismatchException;
import com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency.FavoriteCryptocurrencyExistsException;
import com.example.cryptocurrencytracker.domain.exceptions.favorite_cryptocurrency.FavoriteCryptocurrencyNotFoundException;
import com.example.cryptocurrencytracker.domain.exceptions.user_account.UserNotFoundException;
import com.example.cryptocurrencytracker.domain.models.dtos.FavoriteCryptocrurrencyDTO;
import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import com.example.cryptocurrencytracker.domain.models.entities.FavoriteCryptocurrency;
import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteCryptocurrencyService {

    private final FavoriteCryptocurrencyMapper favoriteCryptocurrencyMapper;
    private final FavoriteCryptocurrencyRepository favoriteCryptocurrencyRepository;
    private final UserAccountRepository userRepository;
    private final CryptocurrencyRepository cryptocurrencyRepository;

    public void addFavoriteCryptocurrency(FavoriteCryptocrurrencyDTO favoriteCryptocrurrencyDTO, String username) {
        try {
            favoriteCryptocurrencyRepository.findByCryptoName(favoriteCryptocrurrencyDTO.cryptoId).isPresent();
        } catch (Exception e) {
            throw new FavoriteCryptocurrencyExistsException();
        }
        FavoriteCryptocurrency favoriteCryptocurrency = favoriteCryptocurrencyMapper.mapToEntity(favoriteCryptocrurrencyDTO);
        if (favoriteCryptocurrency != null) {
            UserAccount user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
            user.getUsersFavoriteCryptocurrencies().add(favoriteCryptocurrency);
            favoriteCryptocurrency.setUser(user);
            Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(favoriteCryptocrurrencyDTO.cryptoId)
                    .orElseThrow(CryptocurrencyNotFoundException::new);
            if (cryptocurrency.getCurrentPrice() > favoriteCryptocrurrencyDTO.desiredSellingPrice ||
                    cryptocurrency.getCurrentPrice() < favoriteCryptocrurrencyDTO.desiredBuyingPrice) {
                throw new DesiredPriceMismatchException();
            }
            cryptocurrency.getUsersFavoriteCryptocurrencies().add(favoriteCryptocurrency);
            favoriteCryptocurrency.setCryptocurrency(cryptocurrency);
            favoriteCryptocurrency.setNotified(false);
            favoriteCryptocurrencyRepository.save(favoriteCryptocurrency);
            userRepository.save(user);
            cryptocurrencyRepository.save(cryptocurrency);
        }
    }

    public Set<FavoriteCryptocurrency> listFavoriteCryptocurrencies(String username) {
        UserAccount user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return user.getUsersFavoriteCryptocurrencies();
    }

    public void updateFavoriteCryptocurrency(FavoriteCryptocrurrencyDTO favoriteCryptocrurrencyDTO, String username) {
        UserAccount user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        FavoriteCryptocurrency favoriteCryptocurrency = favoriteCryptocurrencyRepository.findByUserAndCryptoName(user, favoriteCryptocrurrencyDTO.cryptoId)
                .orElseThrow(FavoriteCryptocurrencyExistsException::new);
        if (favoriteCryptocurrency.getCryptoPrice() > favoriteCryptocrurrencyDTO.desiredSellingPrice ||
                favoriteCryptocurrency.getCryptoPrice() < favoriteCryptocrurrencyDTO.desiredBuyingPrice) {
            throw new DesiredPriceMismatchException();
        }
        favoriteCryptocurrency.setDesiredSellingPrice(favoriteCryptocrurrencyDTO.desiredSellingPrice);
        favoriteCryptocurrency.setDesiredBuyingPrice(favoriteCryptocrurrencyDTO.desiredBuyingPrice);
        favoriteCryptocurrency.setNotified(false);
        favoriteCryptocurrencyRepository.save(favoriteCryptocurrency);
    }

    public void deleteFavoriteCryptocurrency(String cryptoId, String username) {

        UserAccount user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        FavoriteCryptocurrency favoriteCryptocurrency = favoriteCryptocurrencyRepository.findByUserAndCryptoName(user, cryptoId)
                .orElseThrow(FavoriteCryptocurrencyNotFoundException::new);

        favoriteCryptocurrencyRepository.delete(favoriteCryptocurrency);
    }
}
