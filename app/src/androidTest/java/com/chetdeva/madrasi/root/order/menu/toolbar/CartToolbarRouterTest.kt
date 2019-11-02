package com.chetdeva.madrasi.root.order.menu.toolbar

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CartToolbarRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: CartToolbarBuilder.Component
  @Mock internal lateinit var interactor: CartToolbarInteractor
  @Mock internal lateinit var view: CartToolbarView

  private var router: CartToolbarRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = CartToolbarRouter(view, interactor, component)
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

