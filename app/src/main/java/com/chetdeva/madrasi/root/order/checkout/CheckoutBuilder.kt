package com.chetdeva.madrasi.root.order.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
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
 *
 * TODO describe this scope's responsibility as a whole.
 */
class CheckoutBuilder(dependency: ParentComponent) : ViewBuilder<CheckoutView, CheckoutRouter, CheckoutBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CheckoutRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CheckoutRouter].
   */
  fun build(parentViewGroup: ViewGroup): CheckoutRouter {
    val view = createView(parentViewGroup)
    val interactor = CheckoutInteractor()
    val component = DaggerCheckoutBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.checkoutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CheckoutView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return null
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
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
          interactor: CheckoutInteractor): CheckoutRouter {
        return CheckoutRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @CheckoutScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CheckoutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: CheckoutInteractor): Builder

      @BindsInstance
      fun view(view: CheckoutView): Builder

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
