package com.chetdeva.madrasi.root.order.menu.menutoolbar

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MenuToolbarInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: MenuToolbarInteractor.MenuToolbarPresenter
  @Mock internal lateinit var router: MenuToolbarRouter

  private var interactor: MenuToolbarInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestMenuToolbarInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<MenuToolbarInteractor.MenuToolbarPresenter, MenuToolbarRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}