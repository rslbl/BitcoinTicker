package net.resulbal.bitcointicker.data.source

import kotlinx.coroutines.Deferred
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.model.CoinDetail
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by rslbl on 2020-08-20.
 */

interface BitcoinService {

  @GET("coins/list")
  fun getListAsync(): Deferred<List<Coin>>

  @GET("coins/{id}")
  fun getDetailAsync(@Path("id") id: String): Deferred<CoinDetail>
}