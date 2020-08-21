package net.resulbal.bitcointicker.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.resulbal.bitcointicker.ui.coinDetail.CoinDetailFragment
import net.resulbal.bitcointicker.ui.coinList.CoinListFragment
import net.resulbal.bitcointicker.ui.favorite.FavoriteFragment
import net.resulbal.bitcointicker.ui.profile.ProfileFragment

/**
 * Created by rslbl on 2020-08-20.
 */

@Module
abstract class FragmentModule {

  @ContributesAndroidInjector
  abstract fun coinListFragment(): CoinListFragment

  @ContributesAndroidInjector
  abstract fun favoriteFragment(): FavoriteFragment

  @ContributesAndroidInjector
  abstract fun profileFragment(): ProfileFragment

  @ContributesAndroidInjector
  abstract fun coinDetailFragment(): CoinDetailFragment
}