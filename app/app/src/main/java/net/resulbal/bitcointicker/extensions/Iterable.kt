package net.resulbal.bitcointicker.extensions

/**
 * Created by rslbl on 2020-08-21.
 */

fun <T: Any> MutableList<T>.replace(old: T, new: T): MutableList<T> {
  val index = indexOf(old)
  if (index == -1) return this
  this[index] = new
  return this
}

fun <E> Iterable<E>.updated(index: Int, newValue: E) =
  mapIndexed { i, existing -> if (i == index) newValue else existing }

fun <E> Iterable<E>.updated(oldValue: E, newValue: E) =
  map { existing -> if (existing == oldValue) newValue else existing }
