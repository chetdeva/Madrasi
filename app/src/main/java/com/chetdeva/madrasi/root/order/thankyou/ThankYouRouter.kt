package com.chetdeva.madrasi.root.order.thankyou

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ThankYouBuilder.ThankYouScope}.
 */
class ThankYouRouter(
  view: ThankYouView,
  interactor: ThankYouInteractor,
  component: ThankYouBuilder.Component
) : ViewRouter<ThankYouView, ThankYouInteractor, ThankYouBuilder.Component>(
  view,
  interactor,
  component
)
