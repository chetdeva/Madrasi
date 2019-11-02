package com.chetdeva.madrasi.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chetdeva.madrasi.domain.IdleUserHandler
import com.chetdeva.madrasi.root.onboarding.OnboardingBuilder
import com.chetdeva.madrasi.root.onboarding.OnboardingInteractor
import com.chetdeva.madrasi.root.order.OrderBuilder
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
 * Builder for the {@link RootScope}.
 */
class RootBuilder(dependency: ParentComponent) :
  ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.rootRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
    return RootView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun idleUserHandler(): IdleUserHandler
  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: RootView,
        interactor: RootInteractor
      ): RootRouter {
        return RootRouter(
          view,
          interactor,
          component,
          OnboardingBuilder(component),
          OrderBuilder(component)
        )
      }

      @RootScope
      @Provides
      @JvmStatic
      internal fun landingListener(rootInteractor: RootInteractor): OnboardingInteractor.Listener {
        return rootInteractor.LandingListner()
      }
    }
  }

  @RootScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<RootInteractor>, BuilderComponent,
    OnboardingBuilder.ParentComponent, OrderBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RootScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RootInternal
}
