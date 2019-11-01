package com.chetdeva.madrasi.root.order.checkout

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CheckoutRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: CheckoutBuilder.Component
  @Mock internal lateinit var interactor: CheckoutInteractor
  @Mock internal lateinit var view: CheckoutView

  private var router: CheckoutRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = CheckoutRouter(view, interactor, component)
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

