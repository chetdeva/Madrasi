package com.chetdeva.madrasi.root.order.thankyou

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
import com.chetdeva.madrasi.util.addTo
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ThankYouScope].
 */
@RibInteractor
class ThankYouInteractor : Interactor<ThankYouInteractor.ThankYouPresenter, ThankYouRouter>() {

  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var presenter: ThankYouPresenter
  @Inject
  lateinit var phoneNumberInfo: PhoneNumberInfo
  @Inject
  lateinit var orderInfo: OrderInfo
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showPhoneNumber(phoneNumberInfo.phoneNumber)
    presenter.showOrderTotal(orderInfo.total.toPlainString())
    presenter.showOrderId(orderInfo.orderId)

    presenter.orderAgainClickRelay
      .subscribe {
        listener.orderAgain()
      }.addTo(disposable)
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
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
