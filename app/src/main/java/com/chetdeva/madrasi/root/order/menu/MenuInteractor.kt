package com.chetdeva.madrasi.root.order.menu

import com.chetdeva.madrasi.domain.cart.CartManager
import com.chetdeva.madrasi.domain.cart.CartStream
import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem
import com.chetdeva.madrasi.domain.entity.cart.CartResult
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.chetdeva.madrasi.domain.repository.MenuRepository
import com.jakewharton.rxrelay2.Relay
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MenuScope].
 */
@RibInteractor
class MenuInteractor : Interactor<MenuInteractor.MenuPresenter, MenuRouter>() {

  @Inject
  lateinit var presenter: MenuPresenter
  @Inject
  lateinit var menuRepository: MenuRepository
  @Inject
  lateinit var menuId: MenuId
  @Inject
  lateinit var cartManager: CartManager
  @Inject
  lateinit var listener: Listener
  @Inject
  lateinit var cartStream: CartStream

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachMenuToolbar()

    menuRepository.getMenuCategories(menuId.menuId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(presenter::showMenuCategories)

    presenter.addClicks
      .flatMapSingle { cartManager.addItem(CartItem(it, 1)) }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        when (it) {
          is CartResult.AddItemSuccess -> presenter.showMessage(it.cartItem.menuItem.name + " added")
          is CartResult.AddItemError -> presenter.showMessage(it.error.message ?: "")
          is CartResult.UpdateItemSuccess -> presenter.showMessage(it.cartItem.menuItem.name + " updated")
          is CartResult.UpdateItemError -> presenter.showMessage(it.error.message ?: "")
        }
      }

    presenter.checkoutClicks
      .flatMap { cartStream.getCart() }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        listener.checkout(it)
      }
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
