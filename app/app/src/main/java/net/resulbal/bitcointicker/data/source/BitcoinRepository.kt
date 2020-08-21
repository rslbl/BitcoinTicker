package net.resulbal.bitcointicker.data.source

import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.extensions.toResult
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by rslbl on 2020-08-20.
 */

@Singleton
class BitcoinRepository @Inject constructor(private val service: BitcoinService) {

  suspend fun getList(): ApiResult<List<Coin>> = service.getListAsync().toResult()

  suspend fun getDetail(id: String): ApiResult<CoinDetail> = service.getDetailAsync(id).toResult()
}