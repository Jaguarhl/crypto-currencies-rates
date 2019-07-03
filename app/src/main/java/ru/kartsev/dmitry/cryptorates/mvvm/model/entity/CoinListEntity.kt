package ru.kartsev.dmitry.cryptorates.mvvm.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinListEntity(
    @SerializedName("Response")
    @Expose
    var response: String? = null,
    @SerializedName("Message")
    @Expose
    var message: String? = null,
    @SerializedName("BaseImageUrl")
    @Expose
    var baseImageUrl: String? = null,
    @SerializedName("BaseLinkUrl")
    @Expose
    var baseLinkUrl: String? = null,
    @SerializedName("Data")
    @Expose
    var data: Map<String, CryptoDataEntity>? = null,
    @SerializedName("Type")
    @Expose
    var type: Int? = null
)