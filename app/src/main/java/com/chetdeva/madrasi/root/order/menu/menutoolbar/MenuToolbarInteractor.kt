package com.chetdeva.madrasi.root.order.menu.menutoolbar

import com.chetdeva.madrasi.domain.cart.CartCalculator
import com.chetdeva.madrasi.domain.cart.CartStream
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MenuToolbarScope].
 */
@RibInteractor
class MenuToolbarInteractor :
  Interactor<MenuToolbarInteractor.MenuToolbarPresenter, MenuToolbarRouter>() {

  @Inject
  lateinit var presenter: MenuToolbarPresenter
  @Inject
  lateinit var cartStream: CartStream

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    cartStream.getCart()
      .map(CartCalculator::getCartQuantity)
      .subscribe {
        if (it > 0) {
          presenter.showCartIcon()
          presenter.updateCartQuantity(it)
        } else {
          presenter.hideCartIcon()
        }
      }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MenuToolbarPresenter {
    fun updateCartQuantity(cartQuantity: Int)
    fun showCartIcon()
    fun hideCartIcon()
  }
}
