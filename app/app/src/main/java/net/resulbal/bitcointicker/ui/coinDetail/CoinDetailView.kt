package net.resulbal.bitcointicker.ui.coinDetail

import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.ui.base.BaseView

/**
 * Created by rslbl on 2020-08-21.
 */

interface CoinDetailView {

  interface View: BaseView {

  }

  interface ViewModel {

    fun setData(coinDetail: CoinDetail)
  }
}