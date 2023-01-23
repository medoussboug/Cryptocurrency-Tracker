package com.example.cryptocurrencytracker.presentation;

import com.example.cryptocurrencytracker.domain.models.dtos.FavoriteCryptocrurrencyDTO;
import com.example.cryptocurrencytracker.domain.services.FavoriteCryptocurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/cryptocurrencies/favorites")
@AllArgsConstructor
public class FavoriteCryptocurrencyController {
    @Autowired
    private final FavoriteCryptocurrencyService favoriteCryptocurrencyService;

    @GetMapping
    public ResponseEntity<?> listData(Principal principal) {
        return ResponseEntity.ok(favoriteCryptocurrencyService.listFavoriteCryptocurrencies(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<?> loadData(@RequestBody FavoriteCryptocrurrencyDTO favoriteCryptocrurrencyDTO, Principal principal) {
        favoriteCryptocurrencyService.addFavoriteCryptocurrency(favoriteCryptocrurrencyDTO, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateData(@RequestBody FavoriteCryptocrurrencyDTO favoriteCryptocrurrencyDTO, Principal principal) {
        favoriteCryptocurrencyService.updateFavoriteCryptocurrency(favoriteCryptocrurrencyDTO, principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{cryptoId}")
    public ResponseEntity<?> deleteData(@PathVariable String cryptoId, Principal principal) {
        favoriteCryptocurrencyService.deleteFavoriteCryptocurrency(cryptoId, principal.getName());
        return ResponseEntity.ok().build();
    }
}

