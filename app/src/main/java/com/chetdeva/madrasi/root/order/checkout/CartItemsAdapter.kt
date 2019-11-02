package com.chetdeva.madrasi.root.order.checkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.cart.CartItem

class CartItemsAdapter(private val cartItems: MutableList<CartItem>) :
  RecyclerView.Adapter<CartItemsAdapter.CartViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
    return CartViewHolder(view)
  }

  override fun getItemCount(): Int {
    return cartItems.size
  }

  override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    holder.bind(cartItems[position])
  }

  fun setItems(cartItems: List<CartItem>) {
    this.cartItems.clear()
    this.cartItems.addAll(cartItems)
  }

  inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name_textview)
    private val quantityTextView: TextView = itemView.findViewById(R.id.quantity_textview)
    private val quantifiedPriceTextView: TextView = itemView.findViewById(R.id.quantified_price_textview)

    fun bind(cartItem: CartItem) {
      nameTextView.text = cartItem.menuItem.name
      quantityTextView.text = cartItem.quantity.toString()
      val inrQuantifiedPrice =
        itemView.resources.getString(R.string.inr_price, cartItem.quantifiedPrice.toPlainString())
      quantifiedPriceTextView.text = inrQuantifiedPrice
    }
  }
}