package com.chetdeva.madrasi.root.order

import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.domain.cart.MutableCartStream
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.root.RootView
import com.chetdeva.madrasi.root.order.checkout.CheckoutBuilder
import com.chetdeva.madrasi.root.order.checkout.CheckoutInteractor
import com.chetdeva.madrasi.root.order.menu.MenuBuilder
import com.chetdeva.madrasi.root.order.menu.MenuInteractor
import com.chetdeva.madrasi.root.order.thankyou.ThankYouBuilder
import com.chetdeva.madrasi.root.order.thankyou.ThankYouInteractor
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

class OrderBuilder(dependency: ParentComponent) :
  Builder<OrderRouter, OrderBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [OrderRouter].
   *
   * @return a new [OrderRouter].
   */
  fun build(phoneNumberInfo: PhoneNumberInfo): OrderRouter {
    val interactor = OrderInteractor()
    val component = DaggerOrderBuilder_Component.builder()
      .parentComponent(dependency)
      .interactor(interactor)
      .phoneNumber(phoneNumberInfo)
      .build()

    return component.orderRouter()
  }

  interface ParentComponent {
    fun rootView(): RootView
    fun orderListener(): OrderInteractor.Listener
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
      @OrderInternal
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
      internal fun checkoutListener(orderInteractor: OrderInteractor): CheckoutInteractor.Listener {
        return orderInteractor.CheckoutListener()
      }

      @OrderScope
      @Provides
      @JvmStatic
      internal fun thankYouListener(orderInteractor: OrderInteractor): ThankYouInteractor.Listener {
        return orderInteractor.ThankYouListener()
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
          CheckoutBuilder(component),
          ThankYouBuilder(component)
        )
      }
    }
  }

  @OrderScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<OrderInteractor>, BuilderComponent,
    MenuBuilder.ParentComponent, CheckoutBuilder.ParentComponent, ThankYouBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: OrderInteractor): Builder

      @BindsInstance
      fun phoneNumber(phoneNumberInfo: PhoneNumberInfo): Builder

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
