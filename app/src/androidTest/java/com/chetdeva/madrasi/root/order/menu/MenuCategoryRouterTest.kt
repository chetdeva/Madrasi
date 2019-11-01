package com.chetdeva.madrasi.root.order.menu

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MenuCategoryRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: MenuBuilder.Component
  @Mock internal lateinit var interactor: MenuInteractor
  @Mock internal lateinit var view: MenuView

  private var router: MenuRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MenuRouter(view, interactor, component)
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

