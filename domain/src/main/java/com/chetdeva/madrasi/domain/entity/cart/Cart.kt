package com.chetdeva.madrasi.domain.entity.cart

import java.math.BigDecimal

data class Cart(val cartItems: MutableList<CartItem>, val taxes: MutableList<Tax>) {

    val subTotal: BigDecimal
        get() = cartItems
            .map { it.quantifiedPrice }
            .fold(BigDecimal.ZERO, BigDecimal::add)

    val taxTotal: BigDecimal
        get() = taxes
            .map { it.price }
            .fold(BigDecimal.ZERO, BigDecimal::add)

    val total: BigDecimal
        get() = subTotal + taxTotal
}