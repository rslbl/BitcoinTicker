package net.resulbal.bitcointicker.extensions

import java.util.concurrent.TimeUnit

/**
 * Created by rslbl on 2020-08-22.
 */

fun Long.getTime(): String {
  val days = TimeUnit.MILLISECONDS.toDays(this)
  val hours = TimeUnit.MILLISECONDS.toHours(this) % 24
  val minutes = TimeUnit.MILLISECONDS.toMinutes(this) % 60
  val seconds = TimeUnit.MILLISECONDS.toSeconds(this) % 60
  return when {
    days >= 1 -> String.format("%dd days", days)
    hours >= 1 -> String.format("%02d hours", hours)
    minutes >= 1 -> String.format("%02d minutes", minutes)
    else -> String.format("%02d seconds", seconds)
  }
}