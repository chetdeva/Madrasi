package com.chetdeva.madrasi.root.order.checkout

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
import io.reactivex.Single

class CheckoutManager(val phoneNumber: PhoneNumber, val cart: Cart) {

  fun checkout(paymentMode: String): Single<OrderInfo> {
    val orderId = paymentMode + phoneNumber.hashCode().toString() + cart.hashCode().toString()
    return Single.just(OrderInfo(orderId, cart.total))
  }
}