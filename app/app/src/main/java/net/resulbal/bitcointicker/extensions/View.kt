package net.resulbal.bitcointicker.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by rslbl on 2020-08-20.
 */

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
  LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)