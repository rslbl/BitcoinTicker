package net.resulbal.bitcointicker.ui.coinList

import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.ui.base.BaseEvent

/**
 * Created by rslbl on 2020-08-20.
 */

sealed class CoinListEvent: BaseEvent {

  class GoToCoin(val coin: Coin): CoinListEvent()

  class Bookmark(val coin: Coin): CoinListEvent()
}