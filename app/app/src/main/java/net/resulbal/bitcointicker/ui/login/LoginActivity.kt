package net.resulbal.bitcointicker.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_login.loginButton
import net.resulbal.bitcointicker.Constants.RC_SIGN_IN
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.extensions.startActivityClearStack
import net.resulbal.bitcointicker.ui.base.BaseActivity
import net.resulbal.bitcointicker.ui.main.MainActivity
import net.resulbal.bitcointicker.util.FirestoreUtil
import timber.log.Timber

class LoginActivity: BaseActivity() {

  private val singInProviders =
    listOf(
      AuthUI.IdpConfig.EmailBuilder()
        .setAllowNewAccounts(true)
        .setRequireName(true)
        .build()
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    loginButton.setOnClickListener {
      val intent = AuthUI.getInstance().createSignInIntentBuilder()
        .setAvailableProviders(singInProviders)
        .setLogo(R.drawable.ic_monetization_on)
        .build()

      startActivityForResult(intent, RC_SIGN_IN)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == RC_SIGN_IN) {
      val response = IdpResponse.fromResultIntent(data)

      if (resultCode == Activity.RESULT_OK) {
        // TODO: Progress dialog create
        FirestoreUtil.initCurrentUserIfFirstTime { startActivityClearStack<MainActivity>() }
      } else if (resultCode == Activity.RESULT_CANCELED) {
        if (response == null) return

        when (response.error?.errorCode) {
          ErrorCodes.NO_NETWORK -> Timber.e("No network")
          ErrorCodes.UNKNOWN_ERROR -> Timber.e("Unknown Error")
        }
      }
    }
  }
}