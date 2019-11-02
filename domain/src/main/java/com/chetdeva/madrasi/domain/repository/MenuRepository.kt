package com.chetdeva.madrasi.domain.repository

import com.chetdeva.madrasi.domain.entity.menu.Menu
import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import io.reactivex.Maybe
import io.reactivex.Observable

class MenuRepository(private val menuStore: MenuStore) {

  private fun getMenu(menuId: String): Observable<Menu> {
    return Observable.just(menuStore.menu)
      .filter { it.id == menuId }
  }

  fun getMenuCategories(menuId: String): Observable<List<MenuCategory>> {
    return getMenu(menuId)
      .map { it.menuCategories }
      .defaultIfEmpty(emptyList())
  }
}