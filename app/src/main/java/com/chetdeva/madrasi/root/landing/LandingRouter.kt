package com.chetdeva.madrasi.root.landing

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LandingBuilder.LandingScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LandingRouter(
    view: LandingView,
    interactor: LandingInteractor,
    component: LandingBuilder.Component) : ViewRouter<LandingView, LandingInteractor, LandingBuilder.Component>(view, interactor, component)
