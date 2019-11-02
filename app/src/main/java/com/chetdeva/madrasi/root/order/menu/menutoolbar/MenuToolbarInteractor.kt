package com.chetdeva.madrasi.root.order.menu.menutoolbar

import com.chetdeva.madrasi.domain.cart.CartCalculator
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.util.addTo
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MenuToolbarScope].
 */
@RibInteractor
class MenuToolbarInteractor :
  Interactor<MenuToolbarInteractor.MenuToolbarPresenter, MenuToolbarRouter>() {

  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var presenter: MenuToolbarPresenter
  @Inject
  lateinit var cartStream: CartStream

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    cartStream.getCart()
      .map(CartCalculator::getCartItemsQuantity)
      .subscribe {
        if (it > 0) {
          presenter.showCartIcon()
          presenter.updateCartQuantity(it)
        } else {
          presenter.hideCartIcon()
        }
      }.addTo(disposable)
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
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
