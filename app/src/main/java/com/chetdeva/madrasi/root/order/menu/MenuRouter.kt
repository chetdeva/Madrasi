package com.chetdeva.madrasi.root.order.menu

import com.chetdeva.madrasi.root.order.menu.menutoolbar.MenuToolbarBuilder
import com.chetdeva.madrasi.root.order.menu.menutoolbar.MenuToolbarRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MenuBuilder.MenuScope}.
 */
class MenuRouter(
  view: MenuView,
  interactor: MenuInteractor,
  component: MenuBuilder.Component,
  private val menuToolbarBuilder: MenuToolbarBuilder
) : ViewRouter<MenuView, MenuInteractor, MenuBuilder.Component>(view, interactor, component) {

  private var menuToolbarRouter: MenuToolbarRouter? = null

  fun attachMenuToolbar() {
    menuToolbarRouter = menuToolbarBuilder.build(view.getMenuToolbarContainer())
    attachChild(menuToolbarRouter)
    view.getMenuToolbarContainer().addView(menuToolbarRouter!!.view)
  }
}
