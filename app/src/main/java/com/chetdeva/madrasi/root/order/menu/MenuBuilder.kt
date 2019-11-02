package com.chetdeva.madrasi.root.order.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.repository.MenuRepository
import com.chetdeva.madrasi.domain.repository.MenuStore
import com.chetdeva.madrasi.root.order.menu.menutoolbar.MenuToolbarBuilder
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
 * Builder for the {@link MenuScope}.
 */
class MenuBuilder(dependency: ParentComponent) :
  ViewBuilder<MenuView, MenuRouter, MenuBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MenuRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MenuRouter].
   */
  fun build(parentViewGroup: ViewGroup, menuId: MenuId): MenuRouter {
    val view = createView(parentViewGroup)
    val interactor = MenuInteractor()
    val component = DaggerMenuBuilder_Component.builder()
      .parentComponent(dependency)
      .view(view)
      .interactor(interactor)
      .menuId(menuId)
      .build()
    return component.menuRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuView? {
    return MenuView.inflate(inflater, parentViewGroup)
  }

  interface ParentComponent {
    fun cartStream(): CartStream
    fun cartManager(): CartManager
    fun menuListener(): MenuInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @MenuScope
    @Binds
    internal abstract fun presenter(view: MenuView): MenuInteractor.MenuPresenter

    @dagger.Module
    companion object {

      @MenuScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: MenuView,
        interactor: MenuInteractor
      ): MenuRouter {
        return MenuRouter(view, interactor, component, MenuToolbarBuilder(component))
      }

      @MenuScope
      @Provides
      @JvmStatic
      internal fun menuRepository(): MenuRepository {
        return MenuRepository(MenuStore())
      }
    }
  }

  @MenuScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<MenuInteractor>, BuilderComponent,
    MenuToolbarBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: MenuInteractor): Builder

      @BindsInstance
      fun view(view: MenuView): Builder

      @BindsInstance
      fun menuId(menuId: MenuId): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun menuRouter(): MenuRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class MenuScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class MenuInternal
}
