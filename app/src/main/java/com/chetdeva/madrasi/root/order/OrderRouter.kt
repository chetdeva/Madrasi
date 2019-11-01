package com.chetdeva.madrasi.root.order

import android.view.ViewGroup
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.root.landing.LandingRouter
import com.chetdeva.madrasi.root.order.checkout.CheckoutBuilder
import com.chetdeva.madrasi.root.order.menu.MenuBuilder
import com.chetdeva.madrasi.root.order.menu.MenuRouter
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link OrderBuilder.OrderScope}.
 */
class OrderRouter(
  interactor: OrderInteractor,
  component: OrderBuilder.Component,
  private val parentView: ViewGroup,
  private val menuBuilder: MenuBuilder,
  private val checkoutBuilder: CheckoutBuilder
) : Router<OrderInteractor, OrderBuilder.Component>(interactor, component) {

  private var menuRouter: MenuRouter? = null

  internal fun attachMenu(menuId: MenuId) {
    menuRouter = menuBuilder.build(parentView, menuId)
    attachChild(menuRouter)
    parentView.addView(menuRouter!!.view)
  }

  internal fun detachMenu() {
    if (menuRouter != null) {
      detachChild(menuRouter)
      parentView.removeView(menuRouter!!.view)
      menuRouter = null
    }
  }
}
