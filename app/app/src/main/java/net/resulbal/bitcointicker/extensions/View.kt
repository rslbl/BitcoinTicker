package net.resulbal.bitcointicker.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes

/**
 * Created by rslbl on 2020-08-20.
 */

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
  LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun EditText.doOnTextChanged(onTextChanged: (String) -> Unit) {
  addTextChangedListener(object: TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      onTextChanged(s.toString())
    }
  })
}