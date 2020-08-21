package net.resulbal.bitcointicker.ui.favorite

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
 * Created by rslbl on 2020-08-21.
 */

class FavoriteViewModel @Inject constructor(
  private val repository: BitcoinRepository
): BaseViewModel(), FavoriteView.ViewModel {

  private val _favoriteList = MutableLiveData<ApiResult<List<Coin>>>()
  val favoriteList: LiveData<ApiResult<List<Coin>>>?
    get() = _favoriteList

  init {
    start()
  }

  override fun start() {
    launch {
      _favoriteList.postValue(ApiResult.Loading)
      FirestoreUtil.getFavoriteList { _favoriteList.postValue(it.toResult()) }
    }
  }

  override fun updateFavorite(coin: Coin) {
    FirestoreUtil.addOrDeleteFavorites(coin) {
      _favoriteList.value?.let { result ->
        when (result) {
          is ApiResult.Success -> {
            _favoriteList.postValue(result.data.toResult())
          }
        }
      }
    }
  }
}