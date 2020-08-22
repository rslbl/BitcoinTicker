package net.resulbal.bitcointicker.ui.coinDetail

import android.os.CountDownTimer
import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.ui.base.BaseView

/**
 * Created by rslbl on 2020-08-21.
 */

interface CoinDetailView {

  interface View: BaseView {

    fun startTimer(millis: Long)

    fun createTimer(millis: Long): CountDownTimer
  }

  interface ViewModel {

    fun getDetail(coinDetail: CoinDetail)

    fun refresh()

    fun updateFavorite(onSuccess: (Boolean) -> Unit)
  }
}