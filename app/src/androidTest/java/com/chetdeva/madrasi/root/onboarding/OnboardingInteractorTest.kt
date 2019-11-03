package com.chetdeva.madrasi.root.onboarding

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class OnboardingInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: OnboardingInteractor.OnboardingPresenter
  @Mock internal lateinit var router: OnboardingRouter

  private var interactor: OnboardingInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestLandingInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<OnboardingInteractor.OnboardingPresenter, OnboardingRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}