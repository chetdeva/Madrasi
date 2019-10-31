package com.chetdeva.madrasi.root.landing

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LandingScope].
 */
@RibInteractor
class LandingInteractor : Interactor<LandingInteractor.LandingPresenter, LandingRouter>() {

  @Inject
  lateinit var presenter: LandingPresenter
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.orderClicks
      .subscribe {
        listener.order()
      }
  }

  interface Listener {
    fun order()
  }

  interface LandingPresenter {
    val orderClicks: Observable<Unit>
  }
}
