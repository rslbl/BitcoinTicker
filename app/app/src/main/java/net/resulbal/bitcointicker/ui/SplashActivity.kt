package net.resulbal.bitcointicker.ui

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.extensions.startActivityClearStack
import net.resulbal.bitcointicker.ui.base.BaseActivity
import net.resulbal.bitcointicker.ui.login.LoginActivity
import net.resulbal.bitcointicker.ui.main.MainActivity

class SplashActivity: BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    if (FirebaseAuth.getInstance().currentUser == null) {
      startActivityClearStack<LoginActivity>()
    } else {
      startActivityClearStack<MainActivity>()
    }
  }
}