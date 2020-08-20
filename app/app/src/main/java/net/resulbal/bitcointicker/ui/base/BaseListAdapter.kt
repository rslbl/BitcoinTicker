package net.resulbal.bitcointicker.ui.base

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by rslbl on 2020-08-20.
 */

abstract class BaseListAdapter<T: Any, VH: BaseViewHolder<T>>(diffCallback: ItemCallback<T>):
  ListAdapter<T, VH>(diffCallback) {

  override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}