package net.resulbal.bitcointicker.extensions

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.resulbal.bitcointicker.data.source.ApiResult

/**
 * Created by rslbl on 2020-08-20.
 */

inline fun <T> MutableLiveData<ApiResult<T>>.startWithLoading(action: () -> ApiResult<T>) {
  postValue(ApiResult.Loading)
  postValue(action())
}

suspend inline fun <T> Deferred<T>.toResult(): ApiResult<T> {
  return withContext(Dispatchers.IO) {
    try {
      val response = await()
      ApiResult.Success(response)
    } catch (e: Exception) {
      e.printStackTrace()
      ApiResult.Failure(e)
    }
  }
}