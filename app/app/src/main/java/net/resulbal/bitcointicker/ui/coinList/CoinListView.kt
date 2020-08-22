package net.resulbal.bitcointicker.ui.coinList

import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.ui.base.BaseEvent
import net.resulbal.bitcointicker.ui.base.BaseView

/**
 * Created by rslbl on 2020-08-20.
 */

interface CoinListView {

  interface View: BaseView {

    fun dispatch(event: BaseEvent)
  }

  interface ViewModel {

    fun start()

    fun updateFavorite(coin: Coin)

    fun search(searchText: String?)
  }

}