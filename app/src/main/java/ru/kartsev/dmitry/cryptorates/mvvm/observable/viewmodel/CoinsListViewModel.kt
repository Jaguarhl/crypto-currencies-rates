package ru.kartsev.dmitry.cryptorates.mvvm.observable.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.kartsev.dmitry.cryptorates.common.config.NetworkConfig.REFRESH_DELAY
import ru.kartsev.dmitry.cryptorates.common.util.round
import ru.kartsev.dmitry.cryptorates.mvvm.model.entity.CoinsTopEntity
import ru.kartsev.dmitry.cryptorates.mvvm.model.network.api.CryptoApi
import ru.kartsev.dmitry.cryptorates.mvvm.observable.baseobservable.CryptoDataBaseObservable
import timber.log.Timber
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class CoinsListViewModel : ViewModel(), KoinComponent {
    private val cryptoApi: CryptoApi by inject()

    val coinsLiveData: MutableLiveData<List<CryptoDataBaseObservable>> = MutableLiveData()

    fun loadCoinsList(currency: String) {
        cryptoApi.getCoinsTopList(100, currency = currency)
            .map { data ->
                data.data.map {
                CryptoDataBaseObservable(
                    it.coinInfo.url,
                    it.coinInfo.imageUrl,
                    it.coinInfo.name,
                    it.coinInfo.fullName,
                    "${it.display?.get(currency)?.toSymbol} ${it.raw?.get(currency)?.price}"
                )
            } }
            .repeatWhen{completed -> completed.delay(REFRESH_DELAY, TimeUnit.SECONDS)}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<CryptoDataBaseObservable>> {
                override fun onNext(t: List<CryptoDataBaseObservable>) {
                    Timber.d(t.toString())
                    coinsLiveData.postValue(t)
                }

                override fun onComplete() {
                    Timber.d("getCoinsList() completed.")
                }

                override fun onSubscribe(d: Disposable) { }

                override fun onError(e: Throwable) {
                    Timber.e(e, "Error occur: ")
                }
            })
    }

    fun coinItemClicked(observable: CryptoDataBaseObservable) {
        // TODO: Implement click on coin action
    }
}