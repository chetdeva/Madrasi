package com.chetdeva.madrasi.root.order.checkout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.cart.CartItem

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

  private val cartItemsAdapter: CartItemsAdapter by lazy { CartItemsAdapter(mutableListOf()) }

  override fun onFinishInflate() {
    super.onFinishInflate()

    phoneNumberTextView = findViewById(R.id.phone_number_textview)
    cartItemsRecyclerView = findViewById(R.id.cart_items_recyclerview)
    totalTextView = findViewById(R.id.total_textview)

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
    totalTextView.text = total
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): CheckoutView {
      return inflater.inflate(R.layout.checkout_layout, parentViewGroup, false) as CheckoutView
    }
  }
}
