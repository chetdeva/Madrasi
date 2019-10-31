package com.chetdeva.madrasi.root.order

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class OrderRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: OrderBuilder.Component
  @Mock internal lateinit var interactor: OrderInteractor

  private var router: OrderRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = OrderRouter(interactor, component)
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
