package ru.kartsev.dmitry.cryptorates.mvvm.observable.baseobservable

import ru.kartsev.dmitry.cryptorates.mvvm.view.adapter.helper.DiffItemObservable

class CryptoDataBaseObservable(
    val overviewUrl: String,
    val imageUrl: String,
    val coinName: String,
    val fullName: String,
    var price: String
) : DiffItemObservable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CryptoDataBaseObservable) return false

        if (overviewUrl != other.overviewUrl) return false
        if (imageUrl != other.imageUrl) return false
        if (coinName != other.coinName) return false
        if (fullName != other.fullName) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = overviewUrl.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + coinName.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}