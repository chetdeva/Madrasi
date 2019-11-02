package com.chetdeva.madrasi.root.order.thankyou

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ThankYouRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ThankYouBuilder.Component
  @Mock internal lateinit var interactor: ThankYouInteractor
  @Mock internal lateinit var view: ThankYouView

  private var router: ThankYouRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ThankYouRouter(view, interactor, component)
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

