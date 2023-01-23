package com.example.cryptocurrencytracker.data.repositories;

import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, String> {
}
