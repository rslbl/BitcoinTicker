package net.resulbal.bitcointicker.data.model

import kotlinx.android.parcel.Parcelize
import net.resulbal.bitcointicker.ui.base.BaseState

/**
 * Created by rslbl on 2020-08-21.
 */

@Parcelize
data class CoinDetail(
  val id: String? = null,
): BaseState