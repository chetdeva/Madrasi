package com.chetdeva.madrasi.root.order.menu

import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem
import com.chetdeva.madrasi.domain.entity.cart.CartResult
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuInfo
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.chetdeva.madrasi.domain.repository.MenuRepository
import com.chetdeva.madrasi.util.addTo
import com.jakewharton.rxrelay2.Relay
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MenuScope].
 */
@RibInteractor
class MenuInteractor : Interactor<MenuInteractor.MenuPresenter, MenuRouter>() {

  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var presenter: MenuPresenter
  @Inject
  lateinit var menuRepository: MenuRepository
  @Inject
  lateinit var menuInfo: MenuInfo
  @Inject
  lateinit var cartManager: CartManager
  @Inject
  lateinit var listener: Listener
  @Inject
  lateinit var cartStream: CartStream

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.addMenuToolbar()

    menuRepository.getMenuCategories(menuInfo.menuId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(presenter::showMenuCategories)
      .addTo(disposable)

    presenter.addClicks
      .flatMapSingle { cartManager.addItem(CartItem(it, 1)) }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        presenter.showMessage(getCartResultMessage(it))
      }.addTo(disposable)

    presenter.checkoutClicks
      .flatMap { cartStream.getCart() }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        listener.checkout(it)
      }.addTo(disposable)
  }

  private fun getCartResultMessage(cartResult: CartResult): String {
    return when (cartResult) {
      is CartResult.AddItemSuccess -> cartResult.cartItem.quantity.toString() + " " + cartResult.cartItem.menuItem.name + " added"
      is CartResult.AddItemError -> cartResult.error.message ?: ""
      is CartResult.UpdateItemSuccess -> cartResult.cartItem.quantity.toString() + " " + cartResult.cartItem.menuItem.name + " updated"
      is CartResult.UpdateItemError -> cartResult.error.message ?: ""
    }
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MenuPresenter {
    fun showMenuCategories(menuCategories: List<MenuCategory>)
    fun showMessage(message: String)
    val addClicks: Observable<MenuItem>
    val checkoutClicks: Relay<Unit>
  }

  interface Listener {
    fun checkout(cart: Cart)
  }
}
