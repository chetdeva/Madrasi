package com.chetdeva.madrasi.root.order

import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.uber.rib.core.Router

import javax.inject.Inject

/**
 * Coordinates Business Logic for [OrderScope].
 */
@RibInteractor
class OrderInteractor : Interactor<EmptyPresenter, OrderRouter>() {

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
  }
}
