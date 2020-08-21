package net.resulbal.bitcointicker.ui.favorite

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_coin.bookmarkIcon
import kotlinx.android.synthetic.main.item_coin.coinName
import kotlinx.android.synthetic.main.item_coin.coinSymbol
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.model.CoinDiffCallback
import net.resulbal.bitcointicker.extensions.inflate
import net.resulbal.bitcointicker.ui.base.BaseListAdapter
import net.resulbal.bitcointicker.ui.base.BaseViewHolder
import net.resulbal.bitcointicker.ui.base.EventListener
import net.resulbal.bitcointicker.ui.favorite.FavoriteAdapter.ViewHolder

/**
 * Created by rslbl on 2020-08-21.
 */

class FavoriteAdapter(private val listener: EventListener):
  BaseListAdapter<Coin, ViewHolder>(CoinDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(parent.inflate(R.layout.item_coin), listener)

  class ViewHolder(itemView: View, listener: EventListener): BaseViewHolder<Coin>(itemView) {

    init {
      itemView.setOnClickListener { listener(FavoriteEvent.GoToCoin(item)) }
      bookmarkIcon.setOnClickListener { listener(FavoriteEvent.Update(item)) }
    }

    override fun bind(item: Coin) {
      super.bind(item)
      coinName.text = item.name
      coinSymbol.text = item.symbol
      bookmarkIcon.isSelected = item.favorite
    }
  }
}