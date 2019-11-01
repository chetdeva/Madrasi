package com.chetdeva.madrasi.root.landing

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LandingBuilder.LandingScope}.
 */
class LandingRouter(
  view: LandingView,
  interactor: LandingInteractor,
  component: LandingBuilder.Component
) :
  ViewRouter<LandingView, LandingInteractor, LandingBuilder.Component>(view, interactor, component)
