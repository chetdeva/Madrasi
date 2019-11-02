package com.chetdeva.madrasi.root

import com.chetdeva.madrasi.domain.entity.menu.PhoneNumberInfo
import com.chetdeva.madrasi.root.onboarding.OnboardingBuilder
import com.chetdeva.madrasi.root.onboarding.OnboardingRouter
import com.chetdeva.madrasi.root.order.OrderBuilder
import com.chetdeva.madrasi.root.order.OrderRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
  view: RootView,
  interactor: RootInteractor,
  component: RootBuilder.Component,
  private val onboardingBuilder: OnboardingBuilder,
  private val orderBuilder: OrderBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

  private var onboardingRouter: OnboardingRouter? = null
  private var orderRouter: OrderRouter? = null

  internal fun attachOnboarding() {
    onboardingRouter = onboardingBuilder.build(view)
    attachChild(onboardingRouter)
    view.addView(onboardingRouter!!.view)
  }

  internal fun detachOnboarding() {
    if (onboardingRouter != null) {
      detachChild(onboardingRouter)
      view.removeView(onboardingRouter!!.view)
      onboardingRouter = null
    }
  }

  internal fun attachOrder(phoneNumberInfo: PhoneNumberInfo) {
    orderRouter = orderBuilder.build(phoneNumberInfo)
    attachChild(orderRouter)
  }

  internal fun detachOrder() {
    if (orderRouter != null) {
      detachChild(orderRouter)
      orderRouter = null
    }
  }
}
