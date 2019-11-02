package com.chetdeva.madrasi.root.order.thankyou

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.chetdeva.madrasi.R
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * Top level view for {@link ThankYouBuilder.ThankYouScope}.
 */
class ThankYouView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), ThankYouInteractor.ThankYouPresenter {

  private lateinit var orderIdTextView: TextView
  private lateinit var phoneNumberTextView: TextView
  private lateinit var orderTotalTextView: TextView
  private lateinit var orderAgainButton: Button

  private val _orderAgainClickRelay: Relay<Unit> = PublishRelay.create()
  override val orderAgainClickRelay: Observable<Unit>
    get() = _orderAgainClickRelay

  override fun onFinishInflate() {
    super.onFinishInflate()

    orderIdTextView = findViewById(R.id.order_id_textview)
    phoneNumberTextView = findViewById(R.id.phone_number_textview)
    orderTotalTextView = findViewById(R.id.order_total_textview)
    orderAgainButton = findViewById(R.id.order_again_button)

    orderAgainButton.setOnClickListener { _orderAgainClickRelay.accept(Unit) }
  }

  override fun showOrderTotal(total: String) {
    orderTotalTextView.text = resources.getString(R.string.inr_price, total)
  }

  override fun showPhoneNumber(phoneNumber: String) {
    phoneNumberTextView.text = phoneNumber
  }

  override fun showOrderId(orderId: String) {
    orderIdTextView.text = orderId
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): ThankYouView {
      return inflater.inflate(R.layout.thank_you_layout, parentViewGroup, false) as ThankYouView
    }
  }
}
