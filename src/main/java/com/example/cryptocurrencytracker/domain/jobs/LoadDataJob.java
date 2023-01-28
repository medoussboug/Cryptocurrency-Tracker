package com.example.cryptocurrencytracker.domain.jobs;

import com.example.cryptocurrencytracker.domain.services.CryptocurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class LoadDataJob {
    private final CryptocurrencyService cryptocurrencyService;

    @Scheduled(fixedRateString = "PT5M")
    void loadingJob() {
        cryptocurrencyService.loadData();
    }
}
