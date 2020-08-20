package net.resulbal.bitcointicker.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/**
 * Created by rslbl on 2020-08-20.
 */

fun Intent.clearAll(): Intent = apply {
  addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
  addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}


inline fun <reified T: Activity> Context.intentFor(vararg params: Pair<String, Any?>): Intent {
  val arguments = bundleOf(*params)
  return Intent(this, T::class.java).apply { putExtras(arguments) }
}

inline fun <reified T: Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
  val intent = intentFor<T>(*params)
  startActivity(intent)
}

inline fun <reified T: Activity> Context.startActivityClearStack(vararg params: Pair<String, Any?>) {
  val intent = intentFor<T>(*params).apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
  }
  startActivity(intent)
}

inline fun <reified T: Fragment> newInstanceOf(vararg params: Pair<String, Any>): Fragment {
  return T::class.java.newInstance().apply { arguments = bundleOf(*params) }
}