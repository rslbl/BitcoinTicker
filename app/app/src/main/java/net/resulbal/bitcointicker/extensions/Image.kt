package net.resulbal.bitcointicker.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by rslbl on 2020-08-21.
 */

fun ImageView.load(imageUrl: String?) {
  Glide.with(context)
    .load(imageUrl)
    .into(this)
}