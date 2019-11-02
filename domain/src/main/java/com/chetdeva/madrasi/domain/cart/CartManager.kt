package com.chetdeva.madrasi.domain.cart

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem
import com.chetdeva.madrasi.domain.entity.cart.CartResult
import io.reactivex.Completable
import io.reactivex.Single

val EMPTY_CART: Cart = Cart(ArrayList(), ArrayList())

class CartManager(
  private val mutableCartStream: MutableCartStream
) {

  private val cart: Cart = EMPTY_CART

  fun addItem(cartItem: CartItem): Single<CartResult> {
    return Single.fromCallable {
      CartCalculator.addItem(
        cart,
        cartItem
      )
    }
      .map { CartResult.AddItemSuccess(it) as CartResult }
      .doOnSuccess { mutableCartStream.update(cart) }
  }

  fun updateItem(cartItem: CartItem, newQuantity: Int): Single<CartResult> {
    return Single.fromCallable {
      CartCalculator.updateItem(
        cart,
        cartItem,
        newQuantity
      )
    }
      .map { CartResult.UpdateItemSuccess(it) as CartResult }
      .doOnSuccess { mutableCartStream.update(cart) }
  }

  fun removeItem(cartItem: CartItem): Single<Unit> {
    return Single.fromCallable {
      CartCalculator.removeItem(
        cart,
        cartItem
      )
    }
      .doOnSuccess { mutableCartStream.update(cart) }
  }

  fun clearCart(): Completable {
    return Completable.fromAction {
      CartCalculator.clearCart(cart)
    }
  }
}