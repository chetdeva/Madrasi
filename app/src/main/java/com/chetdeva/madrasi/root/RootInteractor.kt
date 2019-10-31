package com.chetdeva.madrasi.root

import com.chetdeva.madrasi.root.landing.LandingInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachLanding()
  }

  inner class LandingListner: LandingInteractor.Listener {
    override fun order() {
      router.detachLanding()
      router.attachOrder()
    }
  }

  interface RootPresenter
}
