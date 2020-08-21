package net.resulbal.bitcointicker.ui.coinList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_coin_list.coinList
import kotlinx.android.synthetic.main.fragment_coin_list.shimmerLayout
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.ui.base.BaseEvent
import net.resulbal.bitcointicker.ui.base.BaseFragment
import net.resulbal.bitcointicker.util.addItemSpacing
import timber.log.Timber
import javax.inject.Inject

class CoinListFragment: BaseFragment(), CoinListView.View {

  private lateinit var viewModel: CoinListViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private lateinit var coinListAdapter: CoinListAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_coin_list, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProvider(this, viewModelFactory).get(CoinListViewModel::class.java)

    coinListAdapter = CoinListAdapter(::dispatch)
    coinList.apply {
      adapter = coinListAdapter
      setHasFixedSize(true)
      addItemSpacing(R.dimen.spacing_normal)
    }

    viewModel.coinList?.observe(requireActivity(), Observer { result ->
      when (result) {
        is ApiResult.Loading -> showShimmer()
        is ApiResult.Failure -> {
          hideShimmer()
          Toast.makeText(requireContext(), "Something wrong...", Toast.LENGTH_SHORT).show()
        }
        is ApiResult.Success -> {
          hideShimmer()
          coinListAdapter.submitList(result.data)
        }
      }
    })
  }

  override fun dispatch(event: BaseEvent) {
    when (event) {
      is CoinListEvent.GoToCoin -> Timber.e("GoToCoin: ${event.coin.name}")
      is CoinListEvent.Update -> viewModel.updateFavorite(event.coin)
    }
  }

  private fun showShimmer() {
    runCatching {
      coinList.isVisible = false
      shimmerLayout.isVisible = true
    }
  }

  private fun hideShimmer() {
    runCatching {
      shimmerLayout.isVisible = false
      coinList.isVisible = true
    }
  }
}