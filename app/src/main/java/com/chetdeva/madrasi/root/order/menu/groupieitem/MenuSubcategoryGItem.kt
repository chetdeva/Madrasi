package com.chetdeva.madrasi.root.order.menu.groupieitem

import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuSubCategory
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.menu_category_item.*


class MenuSubcategoryGItem(private val menuSubCategory: MenuSubCategory) : Item() {

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.name_textview.text = menuSubCategory.name
  }

  override fun getLayout(): Int {
    return R.layout.menu_subcategory_item
  }
}