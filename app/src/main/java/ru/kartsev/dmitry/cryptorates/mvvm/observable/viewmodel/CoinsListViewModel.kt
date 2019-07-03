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
import ru.kartsev.dmitry.cryptorates.mvvm.model.entity.CoinsTopEntity
import ru.kartsev.dmitry.cryptorates.mvvm.model.network.api.CryptoApi
import ru.kartsev.dmitry.cryptorates.mvvm.observable.baseobservable.CryptoDataBaseObservable
import timber.log.Timber

class CoinsListViewModel : ViewModel(), KoinComponent {
    private val cryptoApi: CryptoApi by inject()

    val coinsLiveData: MutableLiveData<List<CryptoDataBaseObservable>> = MutableLiveData()

    fun loadCoinsList(currency: String) {
        cryptoApi.getCoinsTopList(currency)
            .map { CryptoDataBaseObservable.fromCoinsTopEntity(it, currency) }
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