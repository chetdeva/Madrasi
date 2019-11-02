package com.chetdeva.madrasi.root.order.menu.toolbar

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CartToolbarInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: CartToolbarInteractor.CartToolbarPresenter
  @Mock internal lateinit var router: CartToolbarRouter

  private var interactor: CartToolbarInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestCartToolbarInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<CartToolbarInteractor.CartToolbarPresenter, CartToolbarRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}