package com.chetdeva.madrasi.root

import com.chetdeva.madrasi.root.landing.LandingBuilder
import com.chetdeva.madrasi.root.landing.LandingRouter
import com.chetdeva.madrasi.root.order.OrderBuilder
import com.chetdeva.madrasi.root.order.OrderRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
  view: RootView,
  interactor: RootInteractor,
  component: RootBuilder.Component,
  private val landingBuilder: LandingBuilder,
  private val orderBuilder: OrderBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var landingRouter: LandingRouter? = null
  private var orderRouter: OrderRouter? = null

  internal fun attachLanding() {
    landingRouter = landingBuilder.build(view)
    attachChild(landingRouter)
    view.addView(landingRouter!!.view)
  }

  internal fun detachLanding() {
    if (landingRouter != null) {
      detachChild(landingRouter)
      view.removeView(landingRouter!!.view)
      landingRouter = null
    }
  }

  internal fun attachOrder() {
    orderRouter = orderBuilder.build(view)
    attachChild(orderRouter)
  }

  internal fun detachOrder() {
    if (orderRouter != null) {
      detachChild(orderRouter)
      orderRouter = null
    }
  }
}
