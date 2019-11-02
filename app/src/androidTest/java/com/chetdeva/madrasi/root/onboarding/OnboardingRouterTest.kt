package com.chetdeva.madrasi.root.onboarding

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class OnboardingRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: OnboardingBuilder.Component
  @Mock internal lateinit var interactor: OnboardingInteractor
  @Mock internal lateinit var view: OnboardingView

  private var router: OnboardingRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = OnboardingRouter(view, interactor, component)
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

