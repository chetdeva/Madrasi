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

  private fun createFoodSubMenuSubCategories(): List<MenuSubCategory> {
    return listOf(
      MenuSubCategory("11", "1", "Snacks", createSnacksMenuItems()),
      MenuSubCategory("12", "1", "Dosas", createDosaMenuItems())
    )
  }

  private fun createDosaMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("121", "12", "Madrasi Special Dosa", 100.toBigDecimal()),
      MenuItem("122", "12", "Masala Dosa", 90.toBigDecimal()),
      MenuItem("123", "12", "Butter Dosa", 90.toBigDecimal())
    )
  }

  private fun createSnacksMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("111", "11", "Masala Idli", 90.toBigDecimal()),
      MenuItem("112", "11", "Sambar Idli", 70.toBigDecimal()),
      MenuItem("113", "11", "Dahi Idli", 80.toBigDecimal())
    )
  }

  private fun createBeverageMenuSubCategories(): List<MenuSubCategory> {
    return listOf(
      MenuSubCategory("21", "2", "Hot drinks", createHotDrinksMenuItems()),
      MenuSubCategory("22", "2", "Cold drinks", createColdDrinksMenuItems())
    )
  }

  private fun createColdDrinksMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("221", "22", "Pepsi", 40.toBigDecimal()),
      MenuItem("222", "22", "Cold Coffee", 50.toBigDecimal()),
      MenuItem("223", "22", "Lassi", 60.toBigDecimal())
    )
  }

  private fun createHotDrinksMenuItems(): List<MenuItem> {
    return listOf(
      MenuItem("211", "21", "Badam Milk", 60.toBigDecimal()),
      MenuItem("212", "21", "Masala Butter Milk", 50.toBigDecimal()),
      MenuItem("213", "21", "Madrasi Tea", 40.toBigDecimal()),
      MenuItem("214", "21", "Madrasi Coffee", 50.toBigDecimal())
    )
  }
}