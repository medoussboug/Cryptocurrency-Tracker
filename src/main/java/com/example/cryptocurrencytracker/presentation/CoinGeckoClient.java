package com.example.cryptocurrencytracker.presentation;

import com.example.cryptocurrencytracker.domain.models.dtos.CryptocurrencyDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class CoinGeckoClient {
    private static final String URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
    private final HttpClient httpClient;
    private final HttpRequest httpRequest;
    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    CoinGeckoClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.httpRequest = HttpRequest
                .newBuilder(URI.create(URL))
                .GET()
                .build();
    }

    CoinGeckoClient(HttpClient httpClient, HttpRequest httpRequest) {
        this.httpClient = httpClient;
        this.httpRequest = httpRequest;
    }

    public List<CryptocurrencyDTO> getCryptocurrencies() {
        CryptocurrencyDTO[] cryptocurrencyDTOS = gson.fromJson(performRequest(), (Type) CryptocurrencyDTO[].class);
        return List.of(cryptocurrencyDTOS);
    }

    private String performRequest() {
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
