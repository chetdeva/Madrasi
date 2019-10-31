package com.chetdeva.madrasi.root.landing

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.chetdeva.madrasi.R
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

/**
 * Top level view for {@link LandingBuilder.LandingScope}.
 */
class LandingView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), LandingInteractor.LandingPresenter {

  private val _orderClicks: Relay<Unit> = PublishRelay.create()
  override val orderClicks: Observable<Unit>
    get() = _orderClicks.hide()


  override fun onFinishInflate() {
    super.onFinishInflate()
    val orderButton: Button = findViewById(R.id.order_button)
    orderButton.setOnClickListener { _orderClicks.accept(Unit) }
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): LandingView {
      return inflater.inflate(R.layout.landing_layout, parentViewGroup, false) as LandingView
    }
  }
}
