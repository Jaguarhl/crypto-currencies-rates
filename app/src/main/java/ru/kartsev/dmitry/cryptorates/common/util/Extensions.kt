package ru.kartsev.dmitry.cryptorates.common.util

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round((this * multiplier).toInt()) / multiplier
}