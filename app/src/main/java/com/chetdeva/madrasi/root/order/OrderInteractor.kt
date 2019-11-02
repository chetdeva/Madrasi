package com.chetdeva.madrasi.root.order

import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.MenuInfo
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
import com.chetdeva.madrasi.root.order.checkout.CheckoutInteractor
import com.chetdeva.madrasi.root.order.menu.MenuInteractor
import com.chetdeva.madrasi.root.order.thankyou.ThankYouInteractor
import com.chetdeva.madrasi.util.addTo
import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [OrderScope].
 */
@RibInteractor
class OrderInteractor : Interactor<EmptyPresenter, OrderRouter>() {

  private val MADRASI_MENU_Info: MenuInfo = MenuInfo("madrasi123")
  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var phoneNumberInfo: PhoneNumberInfo
  @Inject
  lateinit var listener: Listener
  @Inject
  lateinit var cartManager: CartManager

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachMenu(MADRASI_MENU_Info)
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
  }

  inner class MenuListener : MenuInteractor.Listener {
    override fun checkout(cart: Cart) {
      router.detachMenu()
      router.attachCheckout(phoneNumberInfo, cart)
    }
  }

  inner class CheckoutListener : CheckoutInteractor.Listener {
    override fun checkout(orderInfo: OrderInfo) {
      cartManager.clearCart()
        .subscribe {
          router.detachCheckout()
          router.attachThankYou(phoneNumberInfo, orderInfo)
        }.addTo(disposable)
    }
  }

  inner class ThankYouListener : ThankYouInteractor.Listener {
    override fun orderAgain() {
      router.detachThankYou()
      listener.orderAgain()
    }
  }

  interface Listener {
    fun orderAgain()
  }
}
