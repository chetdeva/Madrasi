package com.chetdeva.madrasi.domain.cart

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.cart.CartItem

/**
 * Single source of truth for [Cart].
 */

object CartCalculator {

  fun addItem(cart: Cart, cartItem: CartItem): CartItem {

    val sameCartItem = cart.cartItems.find {
      isSameCartItem(it, cartItem)
    }

    if (sameCartItem != null) {
      updateQuantity(sameCartItem, sameCartItem.quantity + 1)
      return sameCartItem
    } else {
      updateQuantity(cartItem, 1)
      add(cart, cartItem)
      return cartItem
    }
  }

  private fun isSameCartItem(oldCartItem: CartItem, cartItem: CartItem): Boolean {
    return oldCartItem.menuItem.id == cartItem.menuItem.id
  }

  fun isEmpty(cart: Cart): Boolean {
    return cart.cartItems.isEmpty()
  }

  private fun add(cart: Cart, cartItem: CartItem) {
    cart.cartItems.add(cartItem)
  }

  fun removeItem(cart: Cart, cartItem: CartItem) {
    cart.cartItems.remove(cartItem)
  }

  fun clearCart(cart: Cart) {
    cart.cartItems.clear()
    cart.taxes.clear()
  }

  fun updateItem(cart: Cart, item: CartItem, newQuantity: Int): CartItem {
    getItem(cart, item.menuItem.id)?.let {
      updateQuantity(it, newQuantity)
    }
    return getItem(cart, item.menuItem.id)!!
  }

  fun getItem(cart: Cart, itemId: String): CartItem? {
    return cart.cartItems.find { it.menuItem.id == itemId }
  }

  private fun updateQuantity(item: CartItem, newQuantity: Int) {
    item.quantity = newQuantity
  }

  fun getCartQuantity(cart: Cart): Int {
    return cart.cartItems
      .fold(0) { acc, e -> acc + e.quantity }
  }

  fun getCartItemsQuantity(cart: Cart): Int {
    return cart.cartItems.count()
  }

  fun getQuantity(cart: Cart, itemId: String): Int {
    return cart.cartItems.find { it.menuItem.id == itemId }?.quantity ?: 0
  }
}