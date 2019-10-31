package com.chetdeva.madrasi.root

import com.chetdeva.madrasi.root.landing.LandingBuilder
import com.chetdeva.madrasi.root.landing.LandingRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
  view: RootView,
  interactor: RootInteractor,
  component: RootBuilder.Component,
  private val landingBuilder: LandingBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var landingRouter: LandingRouter? = null


  internal fun attachLanding() {
    landingRouter = landingBuilder.build(view)
    attachChild(landingRouter)
    view.addView(landingRouter!!.view)
  }


  internal fun detachLanding() {
    if (landingRouter != null) {
      detachChild(landingRouter)
      view.removeView(landingRouter!!.view)
      landingRouter = null
    }
  }

  fun attachOrder() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
