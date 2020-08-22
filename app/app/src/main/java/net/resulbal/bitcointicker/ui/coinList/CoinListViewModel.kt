package net.resulbal.bitcointicker.ui.coinList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.resulbal.bitcointicker.data.model.Coin
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.data.source.BitcoinRepository
import net.resulbal.bitcointicker.data.source.toResult
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

  private val _responseList = MutableLiveData<ApiResult<List<Coin>>>()
  val responseList: LiveData<ApiResult<List<Coin>>>?
    get() = _responseList

  private val _favoriteList = MutableLiveData<List<Coin>>()
  val favoriteList: LiveData<List<Coin>>?
    get() = _favoriteList

  override fun start() {
    launch { FirestoreUtil.getFavoriteList { _favoriteList.postValue(it); getList() } }
  }

  private fun getList() {
    launch {
      _coinList.postValue(ApiResult.Loading)
      val response = repository.getList()
      when (response) {
        is ApiResult.Success -> {
          val list = response.data.map { it.updateSelection() }
          _responseList.postValue(list.toResult())
          _coinList.postValue(list.toResult())
        }
      }
    }
  }

  override fun updateFavorite(coin: Coin) {
    FirestoreUtil.addOrDeleteFavorites(coin) {
      coinList?.value.let { result ->
        when (result) {
          is ApiResult.Success -> {
            val list = result.data.map { it.updateSelection() }
            _coinList.postValue(list.toResult())
          }
        }
      }
    }
  }

  override fun search(searchText: String?) {
    if (searchText.isNullOrEmpty()) {
      _coinList.postValue(_responseList.value)
    } else {
      _responseList.value.let { result ->
        when (result) {
          is ApiResult.Success -> {
            val searchList = result.data.filter { it.symbol.contains(searchText) }
            _coinList.postValue(searchList.toResult())
          }
        }
      }
    }
  }

  private fun Coin.updateSelection(): Coin =
    copy(favorite = _favoriteList.value?.any { selected -> selected.id == id } ?: false)
}