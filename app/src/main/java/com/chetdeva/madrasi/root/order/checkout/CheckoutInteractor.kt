package com.chetdeva.madrasi.root.order.checkout

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem
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
 * Coordinates Business Logic for [CheckoutScope].
 */
@RibInteractor
class CheckoutInteractor : Interactor<CheckoutInteractor.CheckoutPresenter, CheckoutRouter>() {

  private val DEFAULT_PAYMENT_METHOD: String = "VISA"
  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var presenter: CheckoutPresenter
  @Inject
  lateinit var phoneNumberInfo: PhoneNumberInfo
  @Inject
  lateinit var cart: Cart
  @Inject
  lateinit var checkoutManager: CheckoutManager
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showPhoneNumber(phoneNumberInfo.phoneNumber)
    presenter.showCartItems(cart.cartItems)
    presenter.showTotal(cart.total.toPlainString())

    presenter.payButtonClicks
      .flatMapSingle { checkoutManager.checkout(DEFAULT_PAYMENT_METHOD) }
      .subscribe(listener::paymentDone)
      .addTo(disposable)
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CheckoutPresenter {
    fun showPhoneNumber(phoneNumber: String)
    fun showCartItems(cartItems: List<CartItem>)
    fun showTotal(total: String)
    val payButtonClicks: Observable<Unit>
  }

  interface Listener {
    fun paymentDone(orderInfo: OrderInfo)
  }
}
