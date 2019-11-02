package com.chetdeva.madrasi.root.order.checkout

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CheckoutScope].
 */
@RibInteractor
class CheckoutInteractor : Interactor<CheckoutInteractor.CheckoutPresenter, CheckoutRouter>() {

  @Inject
  lateinit var presenter: CheckoutPresenter
  @Inject
  lateinit var phoneNumber: PhoneNumber
  @Inject
  lateinit var cart: Cart

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.showPhoneNumber(phoneNumber.phoneNumber)
    presenter.showCartItems(cart.cartItems)
    presenter.showTotal(cart.total.toPlainString())
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CheckoutPresenter {
    fun showPhoneNumber(phoneNumber: String)
    fun showCartItems(cartItems: List<CartItem>)
    fun showTotal(total: String)
  }
}
