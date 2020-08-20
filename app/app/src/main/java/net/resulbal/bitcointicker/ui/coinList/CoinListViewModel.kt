package net.resulbal.bitcointicker.ui.coinList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.extensions.startWithLoading
import net.resulbal.bitcointicker.ui.base.BaseViewModel
import net.resulbal.bitcointicker.util.FirestoreUtil
import javax.inject.Inject

/**
 * Created by rslbl on 2020-08-20.
 */

class CoinListViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), CoinListView.ViewModel {

  private val _coinList = MutableLiveData<ApiResult<List<Coin>>>()
  val coinList: LiveData<ApiResult<List<Coin>>>?
    get() = _coinList

  init {
    launch { _coinList.startWithLoading { repository.getList() } }
  }

  override fun updateFavorite(coin: Coin) {
    FirestoreUtil.updateFavorites(coin.id) {
      // TODO: Update current list
    }
  }
}