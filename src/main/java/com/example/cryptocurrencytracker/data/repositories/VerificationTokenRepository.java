package com.example.cryptocurrencytracker.data.repositories;

import com.example.cryptocurrencytracker.domain.models.entities.UserAccount;
import com.example.cryptocurrencytracker.domain.models.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {

    void deleteByExpiryDateLessThan(LocalDateTime now);

    Optional<VerificationToken> findByUserAccountAndToken(UserAccount userAccount, String token);
}
