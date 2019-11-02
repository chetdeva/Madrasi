package com.chetdeva.madrasi.root.order.menu.menutoolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.root.RootView

/**
 * Top level view for {@link MenuToolbarBuilder.MenuToolbarScope}.
 */
class MenuToolbarView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MenuToolbarInteractor.MenuToolbarPresenter {

  private lateinit var cartQuantityTextView: TextView

  override fun onFinishInflate() {
    super.onFinishInflate()

    cartQuantityTextView = findViewById(R.id.cart_quantity_textview)
  }

  override fun updateCartQuantity(cartQuantity: Int) {
    cartQuantityTextView.text = cartQuantity.toString()
  }

  override fun showCartIcon() {
    cartQuantityTextView.visibility = View.VISIBLE
  }

  override fun hideCartIcon() {
    cartQuantityTextView.visibility = View.GONE
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuToolbarView {
      return inflater.inflate(
        R.layout.menu_toolbar_layout,
        parentViewGroup,
        false
      ) as MenuToolbarView
    }
  }
}
