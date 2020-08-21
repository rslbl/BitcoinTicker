package net.resulbal.bitcointicker.ui.coinDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.resulbal.bitcointicker.data.model.CoinDetail
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.extensions.startWithLoading
import net.resulbal.bitcointicker.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by rslbl on 2020-08-21.
 */

class CoinDetailViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), CoinDetailView.ViewModel {

  private val _coinDetail = MutableLiveData<ApiResult<CoinDetail>>()
  val coinDetail: LiveData<ApiResult<CoinDetail>>?
    get() = _coinDetail

  override fun getDetail(detail: CoinDetail) {
    detail.id?.let { launch { _coinDetail.startWithLoading { repository.getDetail(it) } } }
  }
}