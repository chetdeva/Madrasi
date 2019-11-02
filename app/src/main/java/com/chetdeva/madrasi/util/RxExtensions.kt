package com.chetdeva.madrasi.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

public fun Disposable.addTo(disposable: CompositeDisposable) {
  disposable.add(this)
}
