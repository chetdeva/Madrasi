package com.chetdeva.madrasi.root.order.checkout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.cart.CartItem
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * Top level view for {@link CheckoutBuilder.CheckoutScope}.
 */
class CheckoutView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), CheckoutInteractor.CheckoutPresenter {

  private lateinit var phoneNumberTextView: TextView
  private lateinit var cartItemsRecyclerView: RecyclerView
  private lateinit var totalTextView: TextView
  private lateinit var payButton: Button

  private val _payButtonClickRelay: Relay<Unit> = PublishRelay.create()
  override val payButtonClicks: Observable<Unit>
    get() = _payButtonClickRelay

  private val cartItemsAdapter: CartItemsAdapter by lazy { CartItemsAdapter(mutableListOf()) }

  override fun onFinishInflate() {
    super.onFinishInflate()

    phoneNumberTextView = findViewById(R.id.phone_number_textview)
    cartItemsRecyclerView = findViewById(R.id.cart_items_recyclerview)
    totalTextView = findViewById(R.id.total_textview)
    payButton = findViewById(R.id.pay_button)

    payButton.setOnClickListener { _payButtonClickRelay.accept(Unit) }

    cartItemsRecyclerView.layoutManager = LinearLayoutManager(context)
    cartItemsRecyclerView.adapter = cartItemsAdapter
  }

  override fun showCartItems(cartItems: List<CartItem>) {
    cartItemsAdapter.setItems(cartItems)
    cartItemsAdapter.notifyDataSetChanged()
  }

  override fun showPhoneNumber(phoneNumber: String) {
    phoneNumberTextView.text = phoneNumber
  }

  override fun showTotal(total: String) {
    totalTextView.text = resources.getString(R.string.inr_price, total)
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): CheckoutView {
      return inflater.inflate(R.layout.checkout_layout, parentViewGroup, false) as CheckoutView
    }
  }
}
