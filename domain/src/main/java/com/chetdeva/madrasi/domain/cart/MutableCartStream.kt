package com.chetdeva.madrasi.domain.cart

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

class MutableCartStream: CartStream {

    private val cartRelay: Relay<Cart> = BehaviorRelay.create()

    override fun getCart(): Observable<Cart> {
        return cartRelay.hide()
    }

    fun update(cart: Cart) {
        cartRelay.accept(cart)
    }
}