package net.resulbal.bitcointicker.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State

/**
 * Created by rslbl on 2020-08-20.
 */

class ItemSpacing(
  context: Context,
  @DimenRes spacing: Int
): RecyclerView.ItemDecoration() {

  private val spacing = context.resources.getDimensionPixelSize(spacing)

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
    super.getItemOffsets(outRect, view, parent, state)
    val layoutManager = parent.layoutManager ?: return
    val position = parent.getChildAdapterPosition(view)

    outRect.apply {
      when (layoutManager) {
        is GridLayoutManager -> {
          val spanCount = layoutManager.spanCount // Total column count
          val column = position % spanCount //Current column

          top = if (position < spanCount) 0 else spacing
          left = if (column > 0) spacing else 0
        }
        is LinearLayoutManager -> {
          if (layoutManager.orientation == RecyclerView.VERTICAL) {
            top = if (position > 0) spacing else 0
          } else {
            left = if (position > 0) spacing else 0
          }
        }
      }
    }
  }
}

fun RecyclerView.addItemSpacing(@DimenRes spacing: Int) =
  addItemDecoration(ItemSpacing(context, spacing))