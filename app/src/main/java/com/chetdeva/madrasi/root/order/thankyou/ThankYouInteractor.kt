package com.chetdeva.madrasi.root.order.thankyou

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
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
  lateinit var orderInfo: OrderInfo
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showPhoneNumber(phoneNumber.phoneNumber)
    presenter.showOrderTotal(orderInfo.total.toPlainString())
    presenter.showOrderId(orderInfo.orderId)

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
    fun showOrderTotal(total: String)
    fun showOrderId(orderId: String)
    val orderAgainClickRelay: Observable<Unit>
  }

  interface Listener {
    fun orderAgain()
  }
}
