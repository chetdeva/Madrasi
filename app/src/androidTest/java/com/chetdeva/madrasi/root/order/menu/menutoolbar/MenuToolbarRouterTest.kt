package com.chetdeva.madrasi.root.order.menu.menutoolbar

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MenuToolbarRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: MenuToolbarBuilder.Component
  @Mock internal lateinit var interactor: MenuToolbarInteractor
  @Mock internal lateinit var view: MenuToolbarView

  private var router: MenuToolbarRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MenuToolbarRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

