package net.resulbal.bitcointicker.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by rslbl on 2020-08-20.
 */

@MustBeDocumented
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)