package com.chetdeva.madrasi.root.order.menu.menutoolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chetdeva.madrasi.domain.cart.CartStream
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
 * Builder for the {@link MenuToolbarScope}.
 */
class MenuToolbarBuilder(dependency: ParentComponent) :
  ViewBuilder<MenuToolbarView, MenuToolbarRouter, MenuToolbarBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MenuToolbarRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MenuToolbarRouter].
   */
  fun build(parentViewGroup: ViewGroup): MenuToolbarRouter {
    val view = createView(parentViewGroup)
    val interactor = MenuToolbarInteractor()
    val component = DaggerMenuToolbarBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .build()
    return component.menutoolbarRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuToolbarView? {
    return MenuToolbarView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun cartStream(): CartStream
  }

  @dagger.Module
  abstract class Module {

    @MenuToolbarScope
    @Binds
    internal abstract fun presenter(view: MenuToolbarView): MenuToolbarInteractor.MenuToolbarPresenter

    @dagger.Module
    companion object {

      @MenuToolbarScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: MenuToolbarView,
        interactor: MenuToolbarInteractor
      ): MenuToolbarRouter {
        return MenuToolbarRouter(view, interactor, component)
      }
    }
  }

  @MenuToolbarScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<MenuToolbarInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: MenuToolbarInteractor): Builder

      @BindsInstance
      fun view(view: MenuToolbarView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun menutoolbarRouter(): MenuToolbarRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class MenuToolbarScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class MenuToolbarInternal
}
