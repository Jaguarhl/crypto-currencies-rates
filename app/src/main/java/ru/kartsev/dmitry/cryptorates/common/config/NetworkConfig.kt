package ru.kartsev.dmitry.cryptorates.common.config

object NetworkConfig {
    const val CACHE_SIZE = 30L * 1024 * 1024
    const val REFRESH_DELAY = 1L

    const val BASE_URL = "https://min-api.cryptocompare.com/"
    const val MEDIA_URL = "https://www.cryptocompare.com"
}