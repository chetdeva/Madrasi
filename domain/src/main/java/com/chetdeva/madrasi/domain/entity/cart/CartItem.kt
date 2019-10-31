package com.chetdeva.madrasi.domain.entity.cart

import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import java.math.BigDecimal

data class CartItem(val menuItem: MenuItem, var quantity: Int) {
    val quantifiedPrice: BigDecimal
        get() = menuItem.price * quantity.toBigDecimal()
}