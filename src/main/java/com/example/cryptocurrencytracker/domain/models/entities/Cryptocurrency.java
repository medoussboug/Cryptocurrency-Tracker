package com.example.cryptocurrencytracker.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "current_price")
    private Double currentPrice;
    @Column(name = "market_cap")
    private Double marketCap;
    @Column(name = "market_cap_rank")
    private Double marketCapRank;
    @Column(name = "fully_diluted_valuation")
    private Double fullyDilutedValuation;
    @Column(name = "total_volume")
    private Double totalVolume;
    @Column(name = "high_24h")
    private Double high24h;
    @Column(name = "low_24h")
    private Double low24h;
    @Column(name = "price_change_24h")
    private Double priceChange24h;
    @Column(name = "price_change_percentage_24h")
    private Double priceChangePercentage24h;
    @Column(name = "market_cap_change_24h")
    private Double marketCapChange24h;
    @Column(name = "market_cap_change_percentage_24h")
    private Double marketCapChangePercentage24h;
    @Column(name = "circulating_supply")
    private Double circulatingSupply;
    @Column(name = "total_supply")
    private Double totalSupply;
    @Column(name = "max_supply")
    private Double maxSupply;
    @Column(name = "ath")
    private Double ath;
    @Column(name = "ath_change_percentage")
    private Double athChangePercentage;
    @Column(name = "ath_date")
    private String athDate;
    @Column(name = "atl")
    private Double atl;
    @Column(name = "atl_change_percentage")
    private Double atlChangePercentage;
    @Column(name = "atl_date")
    private String atlDate;
    @Column(name = "last_updated")
    private String lastUpdated;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "cryptocurrency")
    private Set<FavoriteCryptocurrency> usersFavoriteCryptocurrencies = new java.util.LinkedHashSet<>();
}
