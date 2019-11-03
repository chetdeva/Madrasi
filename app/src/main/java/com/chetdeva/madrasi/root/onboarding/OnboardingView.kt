package com.chetdeva.madrasi.root.onboarding

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import com.chetdeva.madrasi.R
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * Top level view for {@link OnboardingBuilder.LandingScope}.
 */
class OnboardingView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), OnboardingInteractor.OnboardingPresenter {

  private val _orderClicks: Relay<String> = PublishRelay.create()
  override val orderClicks: Observable<String>
    get() = _orderClicks.hide()

  override fun onFinishInflate() {
    super.onFinishInflate()

    val orderButton: Button = findViewById(R.id.order_button)
    val mobileNumberEditText: EditText = findViewById(R.id.phone_number_edittext)

    orderButton.setOnClickListener { _orderClicks.accept(mobileNumberEditText.text.toString()) }
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): OnboardingView {
      return inflater.inflate(R.layout.onboarding_layout, parentViewGroup, false) as OnboardingView
    }
  }

  override fun showEmptyPhoneNumberErrorMessage() {
    Toast.makeText(context, R.string.empty_phone_number_error, Toast.LENGTH_SHORT).show()
  }
}
