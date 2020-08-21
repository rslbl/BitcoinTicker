package net.resulbal.bitcointicker.ui.favorite

import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.ui.base.BaseEvent

/**
 * Created by rslbl on 2020-08-21.
 */

sealed class FavoriteEvent: BaseEvent {

  class GoToCoin(val coin: Coin): FavoriteEvent()

  class Update(val coin: Coin): FavoriteEvent()
}