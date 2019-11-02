package com.chetdeva.madrasi.root.order.thankyou

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ThankYouInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ThankYouInteractor.ThankYouPresenter
  @Mock internal lateinit var router: ThankYouRouter

  private var interactor: ThankYouInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestThankYouInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ThankYouInteractor.ThankYouPresenter, ThankYouRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}