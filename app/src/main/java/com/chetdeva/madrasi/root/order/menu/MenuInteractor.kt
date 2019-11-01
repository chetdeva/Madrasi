package com.chetdeva.madrasi.root.order.menu

import com.chetdeva.madrasi.domain.entity.menu.MenuCategory
import com.chetdeva.madrasi.domain.entity.menu.MenuId
import com.chetdeva.madrasi.domain.repository.MenuRepository
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MenuScope].
 */
@RibInteractor
class MenuInteractor : Interactor<MenuInteractor.MenuPresenter, MenuRouter>() {

  @Inject
  lateinit var presenter: MenuPresenter
  @Inject
  lateinit var menuRepository: MenuRepository
  @Inject
  lateinit var menuId: MenuId

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    menuRepository.getMenuCategories(menuId.menuId)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(presenter::showMenuCategories)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MenuPresenter {
    fun showMenuCategories(menuCategories: List<MenuCategory>)
  }
}
