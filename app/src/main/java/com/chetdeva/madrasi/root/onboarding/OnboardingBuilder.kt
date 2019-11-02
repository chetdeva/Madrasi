package com.chetdeva.madrasi.root.onboarding

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
 * Builder for the {@link LandingScope}.
 */
class OnboardingBuilder(dependency: ParentComponent) :
  ViewBuilder<OnboardingView, OnboardingRouter, OnboardingBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [OnboardingRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [OnboardingRouter].
   */
  fun build(parentViewGroup: ViewGroup): OnboardingRouter {
    val view = createView(parentViewGroup)
    val interactor = OnboardingInteractor()
    val component = DaggerOnboardingBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.landingRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): OnboardingView? {
    return OnboardingView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun landingListener(): OnboardingInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @LandingScope
    @Binds
    internal abstract fun presenter(view: OnboardingView): OnboardingInteractor.LandingPresenter

    @dagger.Module
    companion object {

      @LandingScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: OnboardingView,
        interactor: OnboardingInteractor
      ): OnboardingRouter {
        return OnboardingRouter(view, interactor, component)
      }
    }
  }

  @LandingScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<OnboardingInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: OnboardingInteractor): Builder

      @BindsInstance
      fun view(view: OnboardingView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun landingRouter(): OnboardingRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LandingScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LandingInternal
}
