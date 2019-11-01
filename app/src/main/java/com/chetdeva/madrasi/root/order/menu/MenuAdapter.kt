package com.chetdeva.madrasi.root.order.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chetdeva.madrasi.R
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory

class MenuAdapter(private val menuCategories: MutableList<MenuCategory>) :
  RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.menu_category_item, parent, false)
    return MenuViewHolder(view)
  }

  override fun getItemCount(): Int {
    return menuCategories.size
  }

  override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
    holder.bind(menuCategories[position])
  }

  fun setItems(menuCategories: List<MenuCategory>) {
    this.menuCategories.clear()
    this.menuCategories.addAll(menuCategories)
  }

  inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name_textview)

    fun bind(menuCategory: MenuCategory) {
      nameTextView.text = menuCategory.name
    }
  }
}