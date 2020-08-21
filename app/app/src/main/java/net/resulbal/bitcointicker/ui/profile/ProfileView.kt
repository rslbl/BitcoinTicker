package net.resulbal.bitcointicker.ui.profile

import android.content.Context
import net.resulbal.bitcointicker.ui.base.BaseView

/**
 * Created by rslbl on 2020-08-21.
 */

interface ProfileView {

  interface View: BaseView

  interface ViewModel {

    fun start()

    fun logout(context: Context, onSuccess: () -> Unit)
  }
}