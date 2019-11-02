package com.chetdeva.madrasi.domain.entity.cart

sealed class CartResult {
  data class AddItemSuccess(val cartItem: CartItem) : CartResult()
  data class AddItemError(val error: Throwable) : CartResult()
  data class UpdateItemSuccess(val cartItem: CartItem) : CartResult()
  data class UpdateItemError(val error: Throwable) : CartResult()
}