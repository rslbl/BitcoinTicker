package net.resulbal.bitcointicker.ui.coinDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
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
import kotlinx.android.synthetic.main.fragment_coin_detail.refreshButton
import kotlinx.android.synthetic.main.fragment_coin_detail.refreshInputLayout
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.extensions.getTime
import net.resulbal.bitcointicker.extensions.load
import net.resulbal.bitcointicker.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class CoinDetailFragment: BaseFragment(), CoinDetailView.View {

  private lateinit var viewModel: CoinDetailViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val args by navArgs<CoinDetailFragmentArgs>()

  private var timer: CountDownTimer? = null

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
          favoriteButton.text =
            if (result.data.favorite)
              resources.getText(R.string.remove_favorite)
            else
              resources.getText(R.string.add_favorite)
        }
      }
    })

    favoriteButton.setOnClickListener {
      viewModel.updateFavorite { result ->
        favoriteButton.text =
          if (result)
            resources.getText(R.string.remove_favorite)
          else
            resources.getText(R.string.add_favorite)
      }
    }

    refreshButton.setOnClickListener {
      val text = refreshInputLayout.editText?.text.toString()
      if (text.isEmpty()) {
        return@setOnClickListener
      }
      startTimer(text.toLong())
      it.isEnabled = false
      refreshInputLayout?.apply {
        isEnabled = false
        editText?.text?.clear()
      }
    }
  }

  override fun startTimer(millis: Long) {
    timer?.cancel()
    timer = createTimer(millis)
    timer?.start()
  }

  override fun createTimer(millis: Long): CountDownTimer =
    object: CountDownTimer(millis * 1000, 1000) {
      override fun onTick(p0: Long) {
        refreshButton.text = p0.getTime()
      }

      override fun onFinish() {
        refreshButton.text = resources.getText(R.string.SET)
        refreshInputLayout.isEnabled = true
        refreshButton.isEnabled = true
        viewModel.refresh()
      }
    }

  override fun onDestroyView() {
    super.onDestroyView()
    timer?.cancel()
  }
}