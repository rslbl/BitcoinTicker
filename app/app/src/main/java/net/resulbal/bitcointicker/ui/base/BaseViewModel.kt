package net.resulbal.bitcointicker.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Created by rslbl on 2020-08-20.
 */

abstract class BaseViewModel: ViewModel(), CoroutineScope {

  private var job = SupervisorJob()

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Default + job

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}