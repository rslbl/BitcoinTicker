package net.resulbal.bitcointicker.ui.base

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by rslbl on 2020-08-20.
 */

abstract class BaseViewHolder<T: Any>(itemView: View): ViewHolder(itemView), LayoutContainer {

  override val containerView: View?
    get() = itemView

  lateinit var item: T

  @CallSuper
  open fun bind(item: T) {
    this.item = item
  }
}