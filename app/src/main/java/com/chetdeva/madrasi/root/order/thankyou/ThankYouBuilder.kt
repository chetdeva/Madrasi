package com.chetdeva.madrasi.root.order.thankyou

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.domain.entity.order.OrderId
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
 * Builder for the {@link ThankYouScope}.
 */
class ThankYouBuilder(dependency: ParentComponent) :
  ViewBuilder<ThankYouView, ThankYouRouter, ThankYouBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ThankYouRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ThankYouRouter].
   */
  fun build(
    parentViewGroup: ViewGroup,
    phoneNumber: PhoneNumber,
    orderId: OrderId
  ): ThankYouRouter {
    val view = createView(parentViewGroup)
    val interactor = ThankYouInteractor()
    val component = DaggerThankYouBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .phoneNumber(phoneNumber)
      .orderId(orderId)
      .build()
    return component.thankyouRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ThankYouView? {
    return ThankYouView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun thankYouListener(): ThankYouInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @ThankYouScope
    @Binds
    internal abstract fun presenter(view: ThankYouView): ThankYouInteractor.ThankYouPresenter

    @dagger.Module
    companion object {

      @ThankYouScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: ThankYouView,
        interactor: ThankYouInteractor
      ): ThankYouRouter {
        return ThankYouRouter(view, interactor, component)
      }
    }
  }

  @ThankYouScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<ThankYouInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: ThankYouInteractor): Builder

      @BindsInstance
      fun view(view: ThankYouView): Builder

      @BindsInstance
      fun phoneNumber(phoneNumber: PhoneNumber): Builder

      @BindsInstance
      fun orderId(orderId: OrderId): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun thankyouRouter(): ThankYouRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class ThankYouScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class ThankYouInternal
}
