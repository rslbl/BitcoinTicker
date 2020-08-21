package net.resulbal.bitcointicker.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.resulbal.bitcointicker.data.model.User
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.data.source.toResult
import net.resulbal.bitcointicker.ui.base.BaseViewModel
import net.resulbal.bitcointicker.util.FirestoreUtil
import javax.inject.Inject

/**
 * Created by rslbl on 2020-08-21.
 */

class ProfileViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), ProfileView.ViewModel {

  private val _user = MutableLiveData<ApiResult<User>>()
  val user: LiveData<ApiResult<User>>?
    get() = _user

  init {
    start()
  }

  override fun start() {
    launch {
      _user.postValue(ApiResult.Loading)
      FirestoreUtil.getProfile { _user.postValue(it.toResult()) }
    }
  }

  override fun logout(context: Context, onSuccess: () -> Unit) {
    launch { FirestoreUtil.logout(context) { onSuccess() } }
  }
}