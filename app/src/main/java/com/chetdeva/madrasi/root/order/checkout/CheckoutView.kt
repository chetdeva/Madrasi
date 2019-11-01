package com.chetdeva.madrasi.root.order.checkout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chetdeva.madrasi.R

/**
 * Top level view for {@link CheckoutBuilder.CheckoutScope}.
 */
class CheckoutView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : View(context, attrs, defStyle), CheckoutInteractor.CheckoutPresenter {

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): CheckoutView {
      return inflater.inflate(R.layout.checkout_layout, parentViewGroup, false) as CheckoutView
    }
  }
}
