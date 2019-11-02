package com.chetdeva.madrasi.root.order.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link CheckoutScope}.
 */
class CheckoutBuilder(dependency: ParentComponent) :
  ViewBuilder<CheckoutView, CheckoutRouter, CheckoutBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CheckoutRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CheckoutRouter].
   */
  fun build(
    parentViewGroup: ViewGroup,
    phoneNumberInfo: PhoneNumberInfo,
    cart: Cart
  ): CheckoutRouter {
    val view = createView(parentViewGroup)
    val interactor = CheckoutInteractor()
    val component = DaggerCheckoutBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .phoneNumber(phoneNumberInfo)
      .cart(cart)
      .build()
    return component.checkoutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CheckoutView? {
    return CheckoutView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun checkoutListener(): CheckoutInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @CheckoutScope
    @Binds
    internal abstract fun presenter(view: CheckoutView): CheckoutInteractor.CheckoutPresenter

    @dagger.Module
    companion object {

      @CheckoutScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: CheckoutView,
        interactor: CheckoutInteractor
      ): CheckoutRouter {
        return CheckoutRouter(view, interactor, component)
      }

      @CheckoutScope
      @Provides
      @JvmStatic
      internal fun checkoutManager(phoneNumberInfo: PhoneNumberInfo, cart: Cart): CheckoutManager {
        return CheckoutManager(phoneNumberInfo, cart)
      }
    }
  }

  @CheckoutScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<CheckoutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: CheckoutInteractor): Builder

      @BindsInstance
      fun view(view: CheckoutView): Builder

      @BindsInstance
      fun phoneNumber(phoneNumberInfo: PhoneNumberInfo): Builder

      @BindsInstance
      fun cart(cart: Cart): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun checkoutRouter(): CheckoutRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class CheckoutScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class CheckoutInternal
}
