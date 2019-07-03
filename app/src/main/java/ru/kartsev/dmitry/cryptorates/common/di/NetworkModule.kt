package ru.kartsev.dmitry.cryptorates.common.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kartsev.dmitry.cryptorates.common.config.AppConfig.CRYPTO_API_KEY
import ru.kartsev.dmitry.cryptorates.common.config.NetworkConfig.BASE_URL
import ru.kartsev.dmitry.cryptorates.common.config.NetworkConfig.MEDIA_URL
import ru.kartsev.dmitry.cryptorates.mvvm.model.network.api.CryptoApi

object NetworkModule {
    private const val HTTP_RETROFIT_NAME = "network.retrofit"
    private const val HTTP_CLIENT_RETROFIT_NAME = "network.http_client_retrofit"
    private const val HTTP_AUTH_INTERCEPTOR_NAME = "network.http_client_interceptor_retrofit"
    private const val API_CRYPTO = "network.api_crypto"
    private const val PICASSO_NAME = "network.picasso"
    private const val PICASSO_CLIENT_NAME = "network.picasso_client"
    private const val PICASSO_INTERCEPTOR_NAME = "network.picasso_client_interceptor"

    val it: Module = module {
        single(HTTP_AUTH_INTERCEPTOR_NAME) {
            Interceptor {
                val newUrl = it.request().url().newBuilder().
                    build()

                val newRequest = it.request().newBuilder()
                    .url(newUrl)
                    .addHeader("Apikey", CRYPTO_API_KEY)
                    .build()

                it.proceed(newRequest)
            }
        }

        single<OkHttpClient>(HTTP_CLIENT_RETROFIT_NAME) {
            OkHttpClient.Builder()
                .addInterceptor(get(HTTP_AUTH_INTERCEPTOR_NAME))
                .build()
        }

        single<Retrofit>(HTTP_RETROFIT_NAME) {
            Retrofit.Builder().client(get(HTTP_CLIENT_RETROFIT_NAME))
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        single(API_CRYPTO) {
            get<Retrofit>().create(CryptoApi::class.java)
        }

        single<Picasso>(PICASSO_NAME) {
            Picasso.Builder(get())
                .downloader(OkHttp3Downloader(get<OkHttpClient>(PICASSO_CLIENT_NAME)))
                .loggingEnabled(BuildConfig.DEBUG)
                .build()
        }

        single<OkHttpClient>(PICASSO_CLIENT_NAME) {
            OkHttpClient.Builder()
                .addInterceptor(get(PICASSO_INTERCEPTOR_NAME))
                .build()
        }

        single(PICASSO_INTERCEPTOR_NAME) {
            Interceptor {
                val newUrl = it.request().url().newBuilder()
                    .addEncodedPathSegment(MEDIA_URL)
                    .build()

                val newRequest = it.request().newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
        }
    }
}