package com.chetdeva.madrasi.root.order.thankyou

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.domain.entity.order.OrderId
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ThankYouScope].
 */
@RibInteractor
class ThankYouInteractor : Interactor<ThankYouInteractor.ThankYouPresenter, ThankYouRouter>() {

  @Inject
  lateinit var presenter: ThankYouPresenter
  @Inject
  lateinit var phoneNumber: PhoneNumber
  @Inject
  lateinit var orderId: OrderId
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showPhoneNumber(phoneNumber.phoneNumber)
    presenter.showOrderId(orderId.orderId)

    presenter.orderAgainClickRelay
      .subscribe {
        listener.orderAgain()
      }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface ThankYouPresenter {
    fun showPhoneNumber(phoneNumber: String)
    fun showOrderId(orderId: String)
    val orderAgainClickRelay: Observable<Unit>
  }

  interface Listener {
    fun orderAgain()
  }
}
