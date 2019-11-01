package com.chetdeva.madrasi.root.landing

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
class LandingBuilder(dependency: ParentComponent) :
  ViewBuilder<LandingView, LandingRouter, LandingBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [LandingRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [LandingRouter].
   */
  fun build(parentViewGroup: ViewGroup): LandingRouter {
    val view = createView(parentViewGroup)
    val interactor = LandingInteractor()
    val component = DaggerLandingBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.landingRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LandingView? {
    return LandingView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun landingListener(): LandingInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @LandingScope
    @Binds
    internal abstract fun presenter(view: LandingView): LandingInteractor.LandingPresenter

    @dagger.Module
    companion object {

      @LandingScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: LandingView,
        interactor: LandingInteractor
      ): LandingRouter {
        return LandingRouter(view, interactor, component)
      }
    }
  }

  @LandingScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<LandingInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LandingInteractor): Builder

      @BindsInstance
      fun view(view: LandingView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun landingRouter(): LandingRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LandingScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LandingInternal
}
