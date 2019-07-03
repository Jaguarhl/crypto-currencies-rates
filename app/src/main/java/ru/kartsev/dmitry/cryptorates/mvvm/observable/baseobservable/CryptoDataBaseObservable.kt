package ru.kartsev.dmitry.cryptorates.mvvm.observable.baseobservable

import ru.kartsev.dmitry.cryptorates.common.config.NetworkConfig.MEDIA_URL
import ru.kartsev.dmitry.cryptorates.mvvm.model.entity.CoinsTopEntity

data class CryptoDataBaseObservable(
    val overviewUrl: String,
    val imageUrl: String,
    val coinName: String,
    val fullName: String,
    var price: String
) {
    companion object {
        fun fromCoinsTopEntity(data: CoinsTopEntity, currency: String): List<CryptoDataBaseObservable> {
            return data.data.map {
                CryptoDataBaseObservable(
                    it.coinInfo.url,
                    it.coinInfo.imageUrl,
                    it.coinInfo.name,
                    it.coinInfo.fullName,
                    it.display?.get(currency)?.price ?: ""
                )
            }
        }
    }
}