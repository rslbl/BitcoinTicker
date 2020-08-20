package net.resulbal.bitcointicker

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import net.resulbal.bitcointicker.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by rslbl on 2020-08-20.
 */

class BitcoinTickerApp : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.create()
  }

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      Stetho.initializeWithDefaults(this)
    }
  }
}