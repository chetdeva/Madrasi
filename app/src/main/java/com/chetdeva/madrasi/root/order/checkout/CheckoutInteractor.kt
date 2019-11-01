package com.chetdeva.madrasi.root.order.checkout

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CheckoutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CheckoutInteractor : Interactor<CheckoutInteractor.CheckoutPresenter, CheckoutRouter>() {

  @Inject
  lateinit var presenter: CheckoutPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CheckoutPresenter
}
