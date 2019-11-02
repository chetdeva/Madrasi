package com.chetdeva.madrasi.root.onboarding

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumber
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LandingScope].
 */
@RibInteractor
class OnboardingInteractor : Interactor<OnboardingInteractor.LandingPresenter, OnboardingRouter>() {

  @Inject
  lateinit var presenter: LandingPresenter
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.orderClicks
      .subscribe {
        if (it.isNotBlank()) {
          listener.order(PhoneNumber(it))
        } else {
          presenter.showEmptyPhoneNumberErrorMessage()
        }
      }
  }

  interface Listener {
    fun order(phoneNumber: PhoneNumber)
  }

  interface LandingPresenter {
    fun showEmptyPhoneNumberErrorMessage()

    val orderClicks: Observable<String>

  }
}
