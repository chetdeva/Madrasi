package com.chetdeva.madrasi.root.order.checkout

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CheckoutBuilder.CheckoutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CheckoutRouter(
    view: CheckoutView,
    interactor: CheckoutInteractor,
    component: CheckoutBuilder.Component) : ViewRouter<CheckoutView, CheckoutInteractor, CheckoutBuilder.Component>(view, interactor, component)
