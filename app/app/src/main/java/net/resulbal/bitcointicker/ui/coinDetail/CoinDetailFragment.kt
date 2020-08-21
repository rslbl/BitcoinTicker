package net.resulbal.bitcointicker.ui.coinDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_coin_detail.coinImage
import kotlinx.android.synthetic.main.fragment_coin_detail.descriptionText
import kotlinx.android.synthetic.main.fragment_coin_detail.favoriteButton
import kotlinx.android.synthetic.main.fragment_coin_detail.hashText
import kotlinx.android.synthetic.main.fragment_coin_detail.nameText
import kotlinx.android.synthetic.main.fragment_coin_detail.percentageText
import kotlinx.android.synthetic.main.fragment_coin_detail.priceText
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.extensions.load
import net.resulbal.bitcointicker.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class CoinDetailFragment: BaseFragment(), CoinDetailView.View {

  private lateinit var viewModel: CoinDetailViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val args by navArgs<CoinDetailFragmentArgs>()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_coin_detail, container, false)

  @SuppressLint("SetTextI18n")
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailViewModel::class.java)

    if (args.state.id != null) {
      viewModel.getDetail(args.state)
    } else {
      findNavController().popBackStack()
    }

    viewModel.coinDetail?.observe(requireActivity(), Observer { result ->
      when (result) {
        is ApiResult.Loading -> Timber.e("Loading...")
        is ApiResult.Failure -> Timber.e("Failure...")
        is ApiResult.Empty -> Timber.e("Empty...")
        is ApiResult.Success -> {
          coinImage.load(result.data.image?.large ?: result.data.image?.small ?: result.data.image?.thumb)
          nameText.text = result.data.name
          priceText.text = "Current Price: ${result.data.marketData?.currentPrice?.usd ?: "???"} USD"
          hashText.text = "Hashing Algorithm: ${result.data.hashAlgorithm ?: "???"}"
          descriptionText.text = "Description: ${result.data.description?.en ?: "???"}"
          percentageText.text = "Last 24 Hours Price Change Percentage: ${result.data.marketData?.changePercentage ?: "???"}"
        }
      }
    })

    favoriteButton.setOnClickListener {

    }
  }

}