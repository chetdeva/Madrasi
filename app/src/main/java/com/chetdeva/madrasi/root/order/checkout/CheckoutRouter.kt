package com.chetdeva.madrasi.root.order.checkout

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CheckoutBuilder.CheckoutScope}.
 */
class CheckoutRouter(
  view: CheckoutView,
  interactor: CheckoutInteractor,
  component: CheckoutBuilder.Component
) : ViewRouter<CheckoutView, CheckoutInteractor, CheckoutBuilder.Component>(
  view,
  interactor,
  component
) {
}
