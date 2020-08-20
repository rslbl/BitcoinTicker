package net.resulbal.bitcointicker.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.resulbal.bitcointicker.BitcoinTickerApp
import javax.inject.Singleton

/**
 * Created by rslbl on 2020-08-20.
 */

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    FragmentModule::class,
    ViewModelModule::class
  ]
)
interface AppComponent: AndroidInjector<BitcoinTickerApp>