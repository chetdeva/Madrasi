package com.chetdeva.madrasi.root

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.chetdeva.madrasi.root.onboarding.OnboardingInteractor
import com.chetdeva.madrasi.root.order.OrderInteractor
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

    router.attachOnboarding()
  }

  inner class OnboardingListener : OnboardingInteractor.Listener {
    override fun order(phoneNumber: PhoneNumber) {
      router.detachOnboarding()
      router.attachOrder(phoneNumber)
    }
  }

  inner class OrderListener: OrderInteractor.Listener {
    override fun orderAgain() {
      router.detachOrder()
      router.attachOnboarding()
    }
  }

  interface RootPresenter
}
