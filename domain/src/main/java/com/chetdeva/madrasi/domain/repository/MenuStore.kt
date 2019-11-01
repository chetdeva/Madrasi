package com.chetdeva.madrasi.domain.repository

import com.chetdeva.madrasi.domain.entity.menu.Menu
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuItem
import com.chetdeva.madrasi.domain.entity.menu.MenuSubCategory

class MenuStore {

  val menu: Menu by lazy { createMenu() }

  private fun createMenu(): Menu {
    return Menu("madrasi123", "Madrasi", createMenuCategories())
  }

  private fun createMenuCategories(): List<MenuCategory> {
    return listOf(
      MenuCategory("1", "Food", createFoodSubMenuSubCategories()),
      MenuCategory("2", "Beverage", createBeverageMenuSubCategories())
    )
  }

  private fun createFoodSubMenuSubCategories(): MenuSubCategory {
    return MenuSubCategory("11", "1", "Burgers", createBurgersMenuItems())
  }

  private fun createBurgersMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("111", "11", "Cheese Burger", 10.toBigDecimal()),
      MenuItem("112", "11", "BBQ Burger", 12.toBigDecimal()),
      MenuItem("113", "11", "Ham Burger", 12.toBigDecimal())
    )
  }

  private fun createBeverageMenuSubCategories(): MenuSubCategory {
    return MenuSubCategory("21", "2", "Cold Drinks", createColdDrinksMenuItems())
  }

  private fun createColdDrinksMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("211", "21", "Pepsi", 5.toBigDecimal())
    )
  }
}