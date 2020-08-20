package net.resulbal.bitcointicker.extensions

import android.app.ProgressDialog
import android.content.Context

/**
 * Created by rslbl on 2020-08-20.
 */


fun Context.indeterminateProgressDialog(
  message: CharSequence? = null,
  title: CharSequence? = null,
  init: (ProgressDialog.() -> Unit)? = null
) = progressDialog(true, message, title, init)


private fun Context.progressDialog(
  indeterminate: Boolean,
  message: CharSequence? = null,
  title: CharSequence? = null,
  init: (ProgressDialog.() -> Unit)? = null
) = ProgressDialog(this).apply {
  isIndeterminate = indeterminate
  if (!indeterminate) setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
  if (message != null) setMessage(message)
  if (title != null) setTitle(title)
  if (init != null) init()
  show()
}