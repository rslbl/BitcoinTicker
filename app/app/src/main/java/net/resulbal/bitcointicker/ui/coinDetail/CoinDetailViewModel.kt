package net.resulbal.bitcointicker.ui.coinDetail

import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by rslbl on 2020-08-21.
 */

class CoinDetailViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), CoinDetailView.ViewModel {

  override fun setData(coinDetail: CoinDetail) {

  }
}