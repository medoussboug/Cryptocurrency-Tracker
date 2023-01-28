package com.example.cryptocurrencytracker.domain.jobs;

import com.example.cryptocurrencytracker.data.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class UnusedTokensJob {

    private final VerificationTokenRepository verificationTokenRepository;

    @Scheduled(fixedRateString = "PT1M")
    public void deleteUnusedTokens() {
        verificationTokenRepository.deleteByExpiryDateLessThan(LocalDateTime.now());
    }
}
