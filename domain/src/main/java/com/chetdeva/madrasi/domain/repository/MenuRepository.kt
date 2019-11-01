package com.chetdeva.madrasi.domain.repository

import com.chetdeva.madrasi.domain.entity.menu.Menu
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import io.reactivex.Maybe

class MenuRepository(private val menuStore: MenuStore) {

  fun getMenu(menuId: String): Maybe<Menu> {
    return Maybe.just(menuStore.menu)
      .filter { it.id == menuId }
  }

  fun getMenuCategories(menuId: String): Maybe<List<MenuCategory>> {
    return getMenu(menuId)
      .map { it.menuCategories }
      .defaultIfEmpty(emptyList())
  }
}