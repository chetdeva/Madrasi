package com.chetdeva.madrasi.domain.entity.cart

sealed class CartResult {
    data class AddItemSuccess(val cartItemDto: CartItem) : CartResult()
    data class UpdateItemSuccess(val cartItemDto: CartItem) : CartResult()
}