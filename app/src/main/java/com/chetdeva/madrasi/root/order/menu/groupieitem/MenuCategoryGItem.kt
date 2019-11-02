package com.chetdeva.madrasi.root.order.menu.groupieitem

import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.menu_category_item.*

class MenuCategoryGItem(private val menuCategory: MenuCategory) : Item(), ExpandableItem {

    private var onToggleListener: ExpandableGroup? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.name_textview.text = menuCategory.name

        viewHolder.root.setOnClickListener {
            onToggleListener?.onToggleExpanded()
            viewHolder.arrow_imageview.setImageResource(getArrowImageRes())
        }
    }

    private fun getArrowImageRes(): Int {
        return if (onToggleListener?.isExpanded == true) {
            R.drawable.ic_keyboard_arrow_up_black_24dp
        } else {
            R.drawable.ic_keyboard_arrow_down_black_24dp
        }
    }

    override fun getLayout(): Int {
        return R.layout.menu_category_item
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.onToggleListener = onToggleListener
    }
}