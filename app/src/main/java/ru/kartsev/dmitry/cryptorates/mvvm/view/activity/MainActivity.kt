package ru.kartsev.dmitry.cryptorates.mvvm.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.kartsev.dmitry.cryptorates.R
import ru.kartsev.dmitry.cryptorates.mvvm.observable.viewmodel.CoinsListViewModel
import ru.kartsev.dmitry.cryptorates.mvvm.view.adapter.CryptoListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinsListViewModel
    private lateinit var coinsListAdapter: CryptoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CoinsListViewModel::class.java)

        coinsListAdapter = CryptoListAdapter(viewModel)
        coinsListRecyclerView.apply {
            val llm = LinearLayoutManager(context)
            layoutManager = llm
            setHasFixedSize(true)
            adapter = coinsListAdapter
        }

        observeLiveData()

        viewModel.loadCoinsList("USD")
    }

    /** Section: Private Methods. */

    private fun observeLiveData() {
        viewModel.coinsLiveData.observe(this, Observer {
            coinsListAdapter.updateItems(it)
        })
    }
}
