package com.chetdeva.madrasi.root.onboarding

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.util.addTo
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LandingScope].
 */
@RibInteractor
class OnboardingInteractor : Interactor<OnboardingInteractor.OnboardingPresenter, OnboardingRouter>() {

  private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

  @Inject
  lateinit var presenter: OnboardingPresenter
  @Inject
  lateinit var listener: Listener

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.orderClicks
      .subscribe {
        if (it.isNotBlank()) {
          listener.order(PhoneNumberInfo(it))
        } else {
          presenter.showEmptyPhoneNumberErrorMessage()
        }
      }.addTo(disposable)
  }

  override fun willResignActive() {
    super.willResignActive()
    disposable.dispose()
  }

  interface OnboardingPresenter {
    fun showEmptyPhoneNumberErrorMessage()
    val orderClicks: Observable<String>
  }

  interface Listener {
    fun order(phoneNumberInfo: PhoneNumberInfo)
  }
}
