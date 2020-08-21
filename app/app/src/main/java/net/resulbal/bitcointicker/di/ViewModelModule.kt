package net.resulbal.bitcointicker.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.resulbal.bitcointicker.ui.coinDetail.CoinDetailViewModel
import net.resulbal.bitcointicker.ui.coinList.CoinListViewModel
import net.resulbal.bitcointicker.ui.favorite.FavoriteViewModel
import net.resulbal.bitcointicker.ui.profile.ProfileViewModel

/**
 * Created by rslbl on 2020-08-20.
 */

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(CoinListViewModel::class)
  abstract fun coinListViewModel(coinListViewModel: CoinListViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(FavoriteViewModel::class)
  abstract fun favoriteistViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel::class)
  abstract fun profileViewModel(profileViewModel: ProfileViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(CoinDetailViewModel::class)
  abstract fun coinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel
}