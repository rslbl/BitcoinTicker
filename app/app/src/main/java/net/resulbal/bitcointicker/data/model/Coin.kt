package net.resulbal.bitcointicker.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import net.resulbal.bitcointicker.ui.base.BaseState

/**
 * Created by rslbl on 2020-08-20.
 */

@Parcelize
data class Coin(
  @field:SerializedName("id") val id: String,
  @field:SerializedName("symbol") val symbol: String,
  @field:SerializedName("name") val name: String,
  val favorite: Boolean = false
): BaseState {
  constructor(): this("", "", "", false)
}

class CoinDiffCallback: DiffUtil.ItemCallback<Coin>() {
  override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
    oldItem.id == newItem.id

  override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
    oldItem == newItem
}