package net.resulbal.bitcointicker.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_favorite.favoriteList
import kotlinx.android.synthetic.main.fragment_favorite.shimmerLayout
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.ui.base.BaseEvent
import net.resulbal.bitcointicker.ui.base.BaseFragment
import net.resulbal.bitcointicker.ui.coinList.CoinListFragmentDirections
import net.resulbal.bitcointicker.util.addItemSpacing
import javax.inject.Inject

class FavoriteFragment: BaseFragment(), FavoriteView.View {

  private lateinit var viewModel: FavoriteViewModel

  @Inject
  lateinit var viewModelFactoy: ViewModelFactory

  private lateinit var favoriteAdapter: FavoriteAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_favorite, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProvider(this, viewModelFactoy).get(FavoriteViewModel::class.java)

    favoriteAdapter = FavoriteAdapter(::dispatch)
    favoriteList.apply {
      adapter = favoriteAdapter
      setHasFixedSize(true)
      addItemSpacing(R.dimen.spacing_normal)
    }

    viewModel.favoriteList?.observe(requireActivity(), Observer { result ->
      when (result) {
        is ApiResult.Loading -> showShimmer()
        is ApiResult.Empty -> {
          hideShimmer()
          favoriteAdapter.submitList(result.data)
        }
        is ApiResult.Failure -> {
          hideShimmer()
          Toast.makeText(requireContext(), "Something wrong...", Toast.LENGTH_SHORT).show()
        }
        is ApiResult.Success -> {
          hideShimmer()
          favoriteAdapter.submitList(result.data)
        }
      }
    })
  }

  override fun dispatch(event: BaseEvent) {
    when (event) {
      is FavoriteEvent.GoToCoin -> {
        val state = CoinDetail(id = event.coin.id, favorite = true)
        findNavController().navigate(CoinListFragmentDirections.goToCoinDetail(state))
      }
      is FavoriteEvent.Update -> viewModel.updateFavorite(event.coin)
    }
  }

  private fun showShimmer() {
    runCatching {
      favoriteList.isVisible = false
      shimmerLayout.isVisible = true
    }
  }

  private fun hideShimmer() {
    runCatching {
      shimmerLayout.isVisible = false
      favoriteList.isVisible = true
    }
  }
}