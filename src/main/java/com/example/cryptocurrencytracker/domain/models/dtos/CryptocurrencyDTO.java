package com.example.cryptocurrencytracker.domain.models.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CryptocurrencyDTO {
    @SerializedName("id")
    @Expose
    public final String id;
    @SerializedName("symbol")
    @Expose
    public final String symbol;
    @SerializedName("name")
    @Expose
    public final String name;
    @SerializedName("image")
    @Expose
    public final String image;
    @SerializedName("current_price")
    @Expose
    public final Double currentPrice;
    @SerializedName("market_cap")
    @Expose
    public final Double marketCap;
    @SerializedName("market_cap_rank")
    @Expose
    public final Double marketCapRank;
    @SerializedName("fully_diluted_valuation")
    @Expose
    public final Double fullyDilutedValuation;
    @SerializedName("total_volume")
    @Expose
    public final Double totalVolume;
    @SerializedName("high_24h")
    @Expose
    public final Double high24h;
    @SerializedName("low_24h")
    @Expose
    public final Double low24h;
    @SerializedName("price_change_24h")
    @Expose
    public final Double priceChange24h;
    @SerializedName("price_change_percentage_24h")
    @Expose
    public final Double priceChangePercentage24h;
    @SerializedName("market_cap_change_24h")
    @Expose
    public final Double marketCapChange24h;
    @SerializedName("market_cap_change_percentage_24h")
    @Expose
    public final Double marketCapChangePercentage24h;
    @SerializedName("circulating_supply")
    @Expose
    public final Double circulatingSupply;
    @SerializedName("total_supply")
    @Expose
    public final Double totalSupply;
    @SerializedName("max_supply")
    @Expose
    public final Double maxSupply;
    @SerializedName("ath")
    @Expose
    public final Double ath;
    @SerializedName("ath_change_percentage")
    @Expose
    public final Double athChangePercentage;
    @SerializedName("ath_date")
    @Expose
    public final String athDate;
    @SerializedName("atl")
    @Expose
    public final Double atl;
    @SerializedName("atl_change_percentage")
    @Expose
    public final Double atlChangePercentage;
    @SerializedName("atl_date")
    @Expose
    public final String atlDate;
    @SerializedName("last_updated")
    @Expose
    public final String lastUpdated;
}

