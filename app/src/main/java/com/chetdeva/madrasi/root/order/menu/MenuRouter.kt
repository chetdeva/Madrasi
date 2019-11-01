package com.chetdeva.madrasi.root.order.menu

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MenuBuilder.MenuScope}.
 */
class MenuRouter(
  view: MenuView,
  interactor: MenuInteractor,
  component: MenuBuilder.Component
) : ViewRouter<MenuView, MenuInteractor, MenuBuilder.Component>(view, interactor, component) {

}
