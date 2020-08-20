package net.resulbal.bitcointicker.data.source

import kotlinx.coroutines.Deferred
import net.resulbal.bitcointicker.data.model.Coin
import retrofit2.http.GET

/**
 * Created by rslbl on 2020-08-20.
 */

interface BitcoinService {

  @GET("coins/list")
  fun getList(): Deferred<List<Coin>>
}