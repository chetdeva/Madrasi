package com.chetdeva.madrasi.domain

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Defines idle user behaviour, timeout
 */

private const val MAX_IDLE_TIMEOUT = 15L

class IdleUserHandler {

    private val touchRelay: Relay<Unit> = PublishRelay.create()

    /**
     * defines idle user conditions
     */
    fun whenUserIsIdle(): Observable<Unit> {
        return touchRelay.debounce(MAX_IDLE_TIMEOUT, TimeUnit.SECONDS, Schedulers.computation())
    }

    fun onUserTouch() {
        touchRelay.accept(Unit)
    }
}