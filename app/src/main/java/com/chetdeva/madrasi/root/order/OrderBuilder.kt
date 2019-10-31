package com.chetdeva.madrasi.root.order

import android.view.ViewGroup
import com.chetdeva.madrasi.root.RootView
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance

import java.lang.annotation.RetentionPolicy.CLASS

class OrderBuilder(dependency: ParentComponent) : Builder<OrderRouter, OrderBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [OrderRouter].
   *
   * @return a new [OrderRouter].
   */
  fun build(parentViewGroup: ViewGroup): OrderRouter {
    val interactor = OrderInteractor()
    val component = DaggerOrderBuilder_Component.builder()
        .parentComponent(dependency)
        .interactor(interactor)
        .build()

    return component.orderRouter()
  }

  interface ParentComponent {
    fun rootView(): RootView
  }

  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @OrderScope
      @Provides
      @JvmStatic
      internal fun presenter(): EmptyPresenter {
        return EmptyPresenter()
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, interactor: OrderInteractor, rootView: RootView): OrderRouter {
        return OrderRouter(interactor, component, rootView)
      }
    }
  }


  @OrderScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<OrderInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: OrderInteractor): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }

  }

  interface BuilderComponent {
    fun orderRouter(): OrderRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class OrderScope


  @Qualifier
  @Retention(CLASS)
  internal annotation class OrderInternal
}
