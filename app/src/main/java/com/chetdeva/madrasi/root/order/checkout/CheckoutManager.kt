package com.chetdeva.madrasi.root.order.checkout

import com.chetdeva.madrasi.domain.entity.cart.Cart
import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.domain.entity.order.OrderInfo
import io.reactivex.Single

class CheckoutManager(val phoneNumberInfo: PhoneNumberInfo, val cart: Cart) {

  fun checkout(paymentMode: String): Single<OrderInfo> {
    val orderId = paymentMode + phoneNumberInfo.hashCode().toString() + cart.hashCode().toString()
    return Single.just(OrderInfo(orderId, cart.total))
  }
}