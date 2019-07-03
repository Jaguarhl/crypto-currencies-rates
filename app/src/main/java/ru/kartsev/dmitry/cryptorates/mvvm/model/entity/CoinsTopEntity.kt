package ru.kartsev.dmitry.cryptorates.mvvm.model.entity

import com.google.gson.annotations.SerializedName

data class CoinsTopEntity(
    @SerializedName("Data")
    val data: List<Data>,
    @SerializedName("HasWarning")
    val hasWarning: Boolean,
    @SerializedName("Message")
    val message: String,
    @SerializedName("RateLimit")
    val rateLimit: RateLimit,
    @SerializedName("SponsoredData")
    val sponsoredData: List<Any>,
    @SerializedName("Type")
    val type: Int
)

class RateLimit

data class Data(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfoEntity,
    @SerializedName("DISPLAY")
    val display: Map<String, CurrencyDataEntity>? = null,
    @SerializedName("RAW")
    val raw: Map<String, CurrencyDataEntity>? = null
)