package com.chetdeva.madrasi.root.order.menu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.root.RootView

/**
 * Top level view for {@link MenuBuilder.MenuScope}.
 */
class MenuView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), MenuInteractor.MenuPresenter {

  private lateinit var menuRecyclerView: RecyclerView
  private val menuAdapter: MenuAdapter by lazy { MenuAdapter(mutableListOf()) }

  override fun onFinishInflate() {
    super.onFinishInflate()
    menuRecyclerView = findViewById(R.id.menu_recyclerview)
    menuRecyclerView.layoutManager = LinearLayoutManager(context)
    menuRecyclerView.adapter = menuAdapter
  }

  override fun showMenuCategories(menuCategories: List<MenuCategory>) {
    menuAdapter.setItems(menuCategories)
    menuAdapter.notifyDataSetChanged()
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuView {
      return inflater.inflate(R.layout.menu_layout, parentViewGroup, false) as MenuView
    }
  }
}
