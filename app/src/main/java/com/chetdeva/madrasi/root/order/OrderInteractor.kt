package com.chetdeva.madrasi.root.order

import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
import com.chetdeva.madrasi.root.order.checkout.CheckoutInteractor
import com.chetdeva.madrasi.root.order.menu.MenuInteractor
import com.chetdeva.madrasi.root.order.thankyou.ThankYouInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [OrderScope].
 */
@RibInteractor
class OrderInteractor : Interactor<EmptyPresenter, OrderRouter>() {

  private val MADRASI_MENU_ID: MenuId = MenuId("madrasi123")

  @Inject
  lateinit var phoneNumber: PhoneNumber
  @Inject
  lateinit var listener: Listener
  @Inject
  lateinit var cartManager: CartManager

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachMenu(MADRASI_MENU_ID)
  }

  inner class MenuListener : MenuInteractor.Listener {
    override fun checkout(cart: Cart) {
      router.detachMenu()
      router.attachCheckout(phoneNumber, cart)
    }
  }

  inner class CheckoutListener : CheckoutInteractor.Listener {
    override fun checkout(orderInfo: OrderInfo) {
      router.detachCheckout()
      router.attachThankYou(phoneNumber, orderInfo)
    }
  }

  inner class ThankYouListener : ThankYouInteractor.Listener {
    override fun orderAgain() {
      cartManager.clearCart()
        .subscribe {
          router.detachThankYou()
          listener.orderAgain()
        }
    }
  }

  interface Listener {
    fun orderAgain()
  }
}
