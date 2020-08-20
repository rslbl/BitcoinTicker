package net.resulbal.bitcointicker.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.resulbal.bitcointicker.ui.SplashActivity
import net.resulbal.bitcointicker.ui.main.MainActivity

/**
 * Created by rslbl on 2020-08-20.
 */

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  abstract fun splashActivity(): SplashActivity

  @ContributesAndroidInjector
  abstract fun mainActivity(): MainActivity
}