package com.chetdeva.madrasi.root.order.checkout

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CheckoutInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: CheckoutInteractor.CheckoutPresenter
  @Mock internal lateinit var router: CheckoutRouter

  private var interactor: CheckoutInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestCheckoutInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<CheckoutInteractor.CheckoutPresenter, CheckoutRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}