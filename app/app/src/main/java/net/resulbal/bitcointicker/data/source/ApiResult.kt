package net.resulbal.bitcointicker.data.source

/**
 * Created by rslbl on 2020-08-20.
 */

sealed class ApiResult<out T> {

  object Loading: ApiResult<Nothing>()

  object Failure: ApiResult<Nothing>()

  data class Success<T>(val data: T): ApiResult<T>()
}