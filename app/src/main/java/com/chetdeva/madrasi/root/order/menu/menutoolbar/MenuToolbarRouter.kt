package com.chetdeva.madrasi.root.order.menu.menutoolbar

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MenuToolbarBuilder.MenuToolbarScope}.
 */
class MenuToolbarRouter(
  view: MenuToolbarView,
  interactor: MenuToolbarInteractor,
  component: MenuToolbarBuilder.Component
) : ViewRouter<MenuToolbarView, MenuToolbarInteractor, MenuToolbarBuilder.Component>(
  view,
  interactor,
  component
)
