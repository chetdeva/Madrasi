package com.chetdeva.madrasi.root.order.menu

import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.chetdeva.madrasi.root.order.menu.groupieitem.MenuCategoryGItem
import com.chetdeva.madrasi.root.order.menu.groupieitem.MenuItemGItem
import com.chetdeva.madrasi.root.order.menu.groupieitem.MenuSubcategoryGItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Group
import com.xwray.groupie.Section

object MenuAdapterHelper {

  fun createGroups(
    menuCategories: List<MenuCategory>,
    menuItemListener: (MenuItem) -> Unit
  ): List<Group> {

    val groups: MutableList<Group> = mutableListOf()

    menuCategories.forEach { menuCategory ->

      val expandableGroup = ExpandableGroup(MenuCategoryGItem(menuCategory), true)

      menuCategory.menuSubCategories.forEach { menuSubCategory ->

        val subcategoriesSection = Section(MenuSubcategoryGItem(menuSubCategory))

        if (menuSubCategory.menuItems.isNotEmpty()) {
          menuSubCategory.menuItems.forEach { menuItem ->
            subcategoriesSection.add(MenuItemGItem(menuItem, menuItemListener))
          }
        }

        expandableGroup.add(subcategoriesSection)
      }
      groups.add(expandableGroup)
    }

    return groups
  }
}