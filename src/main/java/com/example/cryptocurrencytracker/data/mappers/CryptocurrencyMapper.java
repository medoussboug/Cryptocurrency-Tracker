package com.example.cryptocurrencytracker.data.mappers;

import com.example.cryptocurrencytracker.domain.models.dtos.CryptocurrencyDTO;
import com.example.cryptocurrencytracker.domain.models.entities.Cryptocurrency;
import org.springframework.stereotype.Component;

@Component
public class CryptocurrencyMapper {

    public CryptocurrencyDTO mapToDTO(Cryptocurrency cryptocurrency) {
        return CryptocurrencyDTO.builder()
                .ath(cryptocurrency.getAth())
                .athChangePercentage(cryptocurrency.getAthChangePercentage())
                .athDate(cryptocurrency.getAthDate())
                .atl(cryptocurrency.getAtl())
                .atlChangePercentage(cryptocurrency.getAtlChangePercentage())
                .atlDate(cryptocurrency.getAtlDate())
                .circulatingSupply(cryptocurrency.getCirculatingSupply())
                .currentPrice(cryptocurrency.getCurrentPrice())
                .fullyDilutedValuation(cryptocurrency.getFullyDilutedValuation())
                .high24h(cryptocurrency.getHigh24h())
                .id(cryptocurrency.getId())
                .image(cryptocurrency.getImage())
                .lastUpdated(cryptocurrency.getLastUpdated())
                .low24h(cryptocurrency.getLow24h())
                .marketCap(cryptocurrency.getMarketCap())
                .marketCapChange24h(cryptocurrency.getMarketCapChange24h())
                .marketCapChangePercentage24h(cryptocurrency.getMarketCapChangePercentage24h())
                .marketCapRank(cryptocurrency.getMarketCapRank())
                .maxSupply(cryptocurrency.getMaxSupply())
                .name(cryptocurrency.getName())
                .priceChange24h(cryptocurrency.getPriceChange24h())
                .priceChangePercentage24h(cryptocurrency.getPriceChangePercentage24h())
                .symbol(cryptocurrency.getSymbol())
                .totalSupply(cryptocurrency.getTotalSupply())
                .totalVolume(cryptocurrency.getTotalVolume())
                .build();
    }

    public Cryptocurrency mapToEntity(CryptocurrencyDTO cryptocurrencyDTO) {
        return Cryptocurrency.builder()
                .ath(cryptocurrencyDTO.ath)
                .athChangePercentage(cryptocurrencyDTO.athChangePercentage)
                .athDate(cryptocurrencyDTO.athDate)
                .atl(cryptocurrencyDTO.atl)
                .atlChangePercentage(cryptocurrencyDTO.atlChangePercentage)
                .atlDate(cryptocurrencyDTO.atlDate)
                .circulatingSupply(cryptocurrencyDTO.circulatingSupply)
                .currentPrice(cryptocurrencyDTO.currentPrice)
                .fullyDilutedValuation(cryptocurrencyDTO.fullyDilutedValuation)
                .high24h(cryptocurrencyDTO.high24h)
                .id(cryptocurrencyDTO.id)
                .image(cryptocurrencyDTO.image)
                .lastUpdated(cryptocurrencyDTO.lastUpdated)
                .low24h(cryptocurrencyDTO.low24h)
                .marketCap(cryptocurrencyDTO.marketCap)
                .marketCapChange24h(cryptocurrencyDTO.marketCapChange24h)
                .marketCapChangePercentage24h(cryptocurrencyDTO.marketCapChangePercentage24h)
                .marketCapRank(cryptocurrencyDTO.marketCapRank)
                .maxSupply(cryptocurrencyDTO.maxSupply)
                .name(cryptocurrencyDTO.name)
                .priceChange24h(cryptocurrencyDTO.priceChange24h)
                .priceChangePercentage24h(cryptocurrencyDTO.priceChangePercentage24h)
                .symbol(cryptocurrencyDTO.symbol)
                .totalSupply(cryptocurrencyDTO.totalSupply)
                .totalVolume(cryptocurrencyDTO.totalVolume)
                .build();
    }
}
