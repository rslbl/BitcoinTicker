package net.resulbal.bitcointicker.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import net.resulbal.bitcointicker.BuildConfig
import net.resulbal.bitcointicker.data.source.BitcoinService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by rslbl on 2020-08-20.
 */

@Module
class AppModule {

  @Provides
  @Singleton
  fun provideRetrofit(): BitcoinService {
    val gson = GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
      .create()

    val client = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor())

    val retrofit = Retrofit.Builder()
      .client(client.build())
      .baseUrl(BuildConfig.ENDPOINT)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()

    return retrofit.create(BitcoinService::class.java)
  }
}