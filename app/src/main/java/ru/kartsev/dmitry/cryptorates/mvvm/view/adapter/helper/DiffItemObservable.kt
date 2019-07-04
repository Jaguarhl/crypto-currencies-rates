package ru.kartsev.dmitry.cryptorates.mvvm.view.adapter.helper

interface DiffItemObservable {
    fun id(): Any? = hashCode()
}