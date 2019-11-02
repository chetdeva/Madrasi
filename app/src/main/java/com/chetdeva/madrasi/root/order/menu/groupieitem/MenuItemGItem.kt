package com.chetdeva.madrasi.root.order.menu.groupieitem

import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.menu_category_item.name_textview
import kotlinx.android.synthetic.main.menu_item.*

class MenuItemGItem(
  private val menuItem: MenuItem,
  private val menuItemListener: (MenuItem) -> Unit
) : Item() {

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.name_textview.text = menuItem.name
    viewHolder.add_imagebutton.setOnClickListener {
      menuItemListener(menuItem)
    }
  }

  override fun getLayout(): Int {
    return R.layout.menu_item
  }
}