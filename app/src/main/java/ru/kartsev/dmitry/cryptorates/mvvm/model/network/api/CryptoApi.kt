package ru.kartsev.dmitry.cryptorates.mvvm.model.network.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kartsev.dmitry.cryptorates.mvvm.model.entity.CoinListEntity
import ru.kartsev.dmitry.cryptorates.mvvm.model.entity.CoinsTopEntity

interface CryptoApi {
    @GET("/data/top/totalvolfull")
    fun getCoinsTopList(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
        @Query("tsym") currency: String
    ): Observable<CoinsTopEntity>

    @GET("/data/all/coinlist")
    fun getCoinList(): Observable<CoinListEntity>
}