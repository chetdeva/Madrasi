package com.chetdeva.madrasi.root.order

import android.view.ViewGroup
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.root.order.checkout.CheckoutBuilder
import com.chetdeva.madrasi.root.order.checkout.CheckoutRouter
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
  private var checkoutRouter: CheckoutRouter? = null

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

  internal fun attachCheckout(phoneNumber: PhoneNumber, cart: Cart) {
    checkoutRouter = checkoutBuilder.build(parentView, phoneNumber, cart)
    attachChild(checkoutRouter)
    parentView.addView(checkoutRouter!!.view)
  }

  internal fun detachCheckout() {
    if (checkoutRouter != null) {
      detachChild(checkoutRouter)
      parentView.removeView(checkoutRouter!!.view)
      checkoutRouter = null
    }
  }
}
