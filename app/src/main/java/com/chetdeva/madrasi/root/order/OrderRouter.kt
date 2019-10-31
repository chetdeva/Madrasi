package com.chetdeva.madrasi.root.order

import android.view.ViewGroup
import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link OrderBuilder.OrderScope}.
 */
class OrderRouter(
  interactor: OrderInteractor,
  component: OrderBuilder.Component,
  private val parentView: ViewGroup
) : Router<OrderInteractor, OrderBuilder.Component>(interactor, component) {

  
}
