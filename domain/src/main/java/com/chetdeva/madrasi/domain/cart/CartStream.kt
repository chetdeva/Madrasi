package com.chetdeva.madrasi.domain.cart

import com.chetdeva.madrasi.domain.entity.cart.Cart
import io.reactivex.Observable

interface CartStream {
    fun getCart(): Observable<Cart>
}