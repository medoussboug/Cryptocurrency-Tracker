package com.example.cryptocurrencytracker.presentation;

import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import com.example.cryptocurrencytracker.domain.services.CryptocurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cryptocurrencies")
@RequiredArgsConstructor
public class CryptocurrenciesController {

    private final CryptocurrencyService cryptocurrencyService;

    @GetMapping
    public ResponseEntity<List<Cryptocurrency>>  listData() {
        return ResponseEntity.ok(cryptocurrencyService.listData());
    }
}
