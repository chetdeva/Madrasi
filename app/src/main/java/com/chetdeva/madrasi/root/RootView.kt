package com.chetdeva.madrasi.root

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.chetdeva.madrasi.R

/**
 * Top level view for {@link RootBuilder.RootScope}.
 */
class RootView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), RootInteractor.RootPresenter {

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
      return inflater.inflate(R.layout.root_layout, parentViewGroup, false) as RootView
    }
  }
}
