package com.chetdeva.madrasi.root.order

import android.view.ViewGroup
import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.domain.cart.MutableCartStream
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.root.RootView
import com.chetdeva.madrasi.root.order.checkout.CheckoutBuilder
import com.chetdeva.madrasi.root.order.menu.MenuBuilder
import com.chetdeva.madrasi.root.order.menu.MenuInteractor
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance

import java.lang.annotation.RetentionPolicy.CLASS

class OrderBuilder(dependency: ParentComponent) :
  Builder<OrderRouter, OrderBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [OrderRouter].
   *
   * @return a new [OrderRouter].
   */
  fun build(phoneNumber: PhoneNumber): OrderRouter {
    val interactor = OrderInteractor()
    val component = DaggerOrderBuilder_Component.builder()
      .parentComponent(dependency)
      .interactor(interactor)
      .phoneNumber(phoneNumber)
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
      internal fun mutableCartStream(): MutableCartStream {
        return MutableCartStream()
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun cartStream(mutableCartStream: MutableCartStream): CartStream {
        return mutableCartStream
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun cartManager(mutableCartStream: MutableCartStream): CartManager {
        return CartManager(mutableCartStream)
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun menuListener(orderInteractor: OrderInteractor): MenuInteractor.Listener {
        return orderInteractor.MenuListener()
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        interactor: OrderInteractor,
        rootView: RootView
      ): OrderRouter {
        return OrderRouter(
          interactor,
          component,
          rootView,
          MenuBuilder(component),
          CheckoutBuilder(component)
        )
      }
    }
  }

  @OrderScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<OrderInteractor>, BuilderComponent,
    MenuBuilder.ParentComponent, CheckoutBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: OrderInteractor): Builder

      @BindsInstance
      fun phoneNumber(phoneNumber: PhoneNumber): Builder

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
