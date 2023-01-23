package com.example.cryptocurrencytracker.data.repositories;

import com.example.cryptocurrencytracker.domain.models.entities.FavoriteCryptocurrency;
import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteCryptocurrencyRepository extends JpaRepository<FavoriteCryptocurrency, Long> {
    Optional<FavoriteCryptocurrency> findByCryptoName(String cryptoName);

    Optional<FavoriteCryptocurrency> findByUserAndCryptoName(UserAccount user, String cyptoName);
}
