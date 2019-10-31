package com.chetdeva.madrasi.root.landing

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LandingRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: LandingBuilder.Component
  @Mock internal lateinit var interactor: LandingInteractor
  @Mock internal lateinit var view: LandingView

  private var router: LandingRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = LandingRouter(view, interactor, component)
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

