package com.example.cryptocurrencytracker;

import com.example.cryptocurrencytracker.domain.services.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CryptocurrencyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptocurrencyTrackerApplication.class, args);
    }

    @Autowired
    CryptocurrencyService cryptocurrencyService;

    @Scheduled(fixedRateString = "PT5M")
    void loadingJob() {
        cryptocurrencyService.loadData();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
