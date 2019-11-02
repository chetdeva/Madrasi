package com.chetdeva.madrasi.domain.entity.menu

data class MenuCategory(val id: String, val name: String, val menuSubCategories: List<MenuSubCategory>)