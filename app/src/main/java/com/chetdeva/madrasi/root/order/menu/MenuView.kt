package com.chetdeva.madrasi.root.order.menu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.chetdeva.madrasi.root.order.menu.MenuAdapterHelper.createGroups
import com.chetdeva.madrasi.root.order.menu.groupieitem.MenuItemGItem
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable

/**
 * Top level view for {@link MenuBuilder.MenuScope}.
 */
class MenuView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), MenuInteractor.MenuPresenter {

  private lateinit var menuToolbarContainer: FrameLayout
  private lateinit var checkoutButton: Button

  private val _checkoutClickRelay: Relay<Unit> = PublishRelay.create()
  override val checkoutClicks: Relay<Unit>
    get() = _checkoutClickRelay

  private val _addClickRelay: Relay<MenuItem> = PublishRelay.create()
  override val addClicks: Observable<MenuItem>
    get() = _addClickRelay

  private val menuAdapter: GroupAdapter<GroupieViewHolder> by lazy { GroupAdapter<GroupieViewHolder>() }

  override fun onFinishInflate() {
    super.onFinishInflate()

    menuToolbarContainer = findViewById(R.id.menu_toolbar_container)
    checkoutButton = findViewById(R.id.checkout_button)

    checkoutButton.setOnClickListener { _checkoutClickRelay.accept(Unit) }

    setupRecyclerView()
  }

  private fun setupRecyclerView() {
    val menuRecyclerView: RecyclerView = findViewById(R.id.menu_recyclerview)
    menuRecyclerView.layoutManager = LinearLayoutManager(context)
    menuRecyclerView.adapter = menuAdapter
  }

  override fun showMenuCategories(menuCategories: List<MenuCategory>) {
    val groups = createGroups(menuCategories, _addClickRelay::accept)
    menuAdapter.addAll(groups)
  }

  fun getMenuToolbarContainer(): FrameLayout {
    return menuToolbarContainer
  }

  override fun showMessage(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
  }

  companion object {
    @JvmStatic
    fun inflate(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuView {
      return inflater.inflate(R.layout.menu_layout, parentViewGroup, false) as MenuView
    }
  }
}
