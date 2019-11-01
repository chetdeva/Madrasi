package com.chetdeva.madrasi.domain.entity.menu

import java.math.BigDecimal

data class MenuItem(val id: String, val subMenuId: String, val name: String, val price: BigDecimal)