package net.resulbal.bitcointicker.data.source

import net.resulbal.bitcointicker.data.source.ApiResult.Empty
import net.resulbal.bitcointicker.data.source.ApiResult.Failure
import net.resulbal.bitcointicker.data.source.ApiResult.Success

/**
 * Created by rslbl on 2020-08-20.
 */

sealed class ApiResult<out T> {

  object Loading: ApiResult<Nothing>()

  class Failure(val exception: Throwable): ApiResult<Nothing>()

  data class Empty<T>(val data: T?): ApiResult<T>()

  data class Success<T>(val data: T): ApiResult<T>()
}

fun <T> T?.toResult(): ApiResult<T> = if (this == null) Empty(null) else Success(this)

fun <T> List<T>?.toResult(): ApiResult<List<T>> =
  if (this == null || this.isEmpty()) Empty(emptyList()) else Success(this)

fun <T> Throwable.toResult(): ApiResult<T> = Failure(this)