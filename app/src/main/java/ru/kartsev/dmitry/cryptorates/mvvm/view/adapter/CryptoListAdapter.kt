package ru.kartsev.dmitry.cryptorates.mvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.koin.standalone.KoinComponent
import ru.kartsev.dmitry.cryptorates.R
import ru.kartsev.dmitry.cryptorates.databinding.ItemCryptoCurrencyBinding
import ru.kartsev.dmitry.cryptorates.mvvm.observable.baseobservable.CryptoDataBaseObservable
import ru.kartsev.dmitry.cryptorates.mvvm.observable.viewmodel.CoinsListViewModel
import ru.kartsev.dmitry.cryptorates.mvvm.view.adapter.helper.DefaultDiffCallback

class CryptoListAdapter(
    private val viewModel: CoinsListViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    KoinComponent {
    /** Section: Private Properties. */
    private val items: MutableList<CryptoDataBaseObservable> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater, R.layout.item_crypto_currency,
            parent, false
        ) as ItemCryptoCurrencyBinding

        return ItemCryptoCurrencyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val observable = items[position]

        with(holder as ItemCryptoCurrencyViewHolder) {
            bind(observable, viewModel)
        }
    }

    fun updateItems(list: List<CryptoDataBaseObservable>) {
        val callback = DefaultDiffCallback(items, list)
        val result = DiffUtil.calculateDiff(callback)

        items.apply {
            clear()
            addAll(list)
        }

        result.dispatchUpdatesTo(this)
    }

    class ItemCryptoCurrencyViewHolder(
        private val binding: ItemCryptoCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            observable: CryptoDataBaseObservable,
            viewModel: CoinsListViewModel) {
            binding.baseObservable = observable
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}