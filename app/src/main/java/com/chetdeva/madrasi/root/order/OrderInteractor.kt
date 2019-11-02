package com.chetdeva.madrasi.root.order

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.root.order.menu.MenuInteractor
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

  private val MADRASI_MENU_ID: MenuId = MenuId("madrasi123")

  @Inject
  lateinit var phoneNumber: PhoneNumber

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachMenu(MADRASI_MENU_ID)
  }

  inner class MenuListener: MenuInteractor.Listener {
    override fun onCheckoutClicked(cart: Cart) {
      router.detachMenu()
      router.attachCheckout(phoneNumber, cart)
    }
  }
}
