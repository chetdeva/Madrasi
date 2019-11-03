package com.chetdeva.madrasi.root.onboarding

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link OnboardingBuilder.LandingScope}.
 */
class OnboardingRouter(
  view: OnboardingView,
  interactor: OnboardingInteractor,
  component: OnboardingBuilder.Component
) :
  ViewRouter<OnboardingView, OnboardingInteractor, OnboardingBuilder.Component>(view, interactor, component) {
}
