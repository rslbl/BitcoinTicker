package net.resulbal.bitcointicker.ui.coinDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.data.source.toResult
import net.resulbal.bitcointicker.ui.base.BaseViewModel
import net.resulbal.bitcointicker.util.FirestoreUtil
import javax.inject.Inject

/**
 * Created by rslbl on 2020-08-21.
 */

class CoinDetailViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), CoinDetailView.ViewModel {

  private var coinId: String? = null
  private var favorite: Boolean = false

  private val _coinDetail = MutableLiveData<ApiResult<CoinDetail>>()
  val coinDetail: LiveData<ApiResult<CoinDetail>>?
    get() = _coinDetail

  override fun getDetail(detail: CoinDetail) {
    if (detail.id == null) return

    this.coinId = detail.id
    this.favorite = detail.favorite

    launch {
      val response = repository.getDetail(detail.id)
      when (response) {
        is ApiResult.Success -> setData(response.data)
      }
    }
  }

  override fun refresh() {
    if (coinId == null) return

    launch {
      val response = repository.getDetail(coinId ?: "")
      when (response) {
        is ApiResult.Success -> setData(response.data)
      }
    }
  }

  private fun setData(detail: CoinDetail) {
    var data = detail
    data = data.copy(favorite = detail.favorite)
    _coinDetail.postValue(data.toResult())
  }

  override fun updateFavorite(onSuccess: (Boolean) -> Unit) {
    launch {
      _coinDetail.value?.let { result ->
        when (result) {
          is ApiResult.Success -> {
            favorite = !favorite
            val coin = Coin(
              id = result.data.id ?: "",
              symbol = result.data.symbol ?: "",
              name = result.data.name ?: "",
              favorite = favorite
            )
            FirestoreUtil.addOrDeleteFavorites(coin) { onSuccess(favorite) }
          }
        }
      }
    }
  }
}