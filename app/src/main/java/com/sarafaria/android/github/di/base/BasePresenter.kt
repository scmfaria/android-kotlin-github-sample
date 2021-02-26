package com.sarafaria.android.github.di.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter <V> {

    private val disposable = CompositeDisposable()

    private var view: V? = null

    val isBinded: Boolean
        get() = view != null

    open fun bind(view: V) {
        this.view = view
    }

    open fun unbind() {
        disposable.clear()
        this.view = null
    }

    fun subscription(): CompositeDisposable {
        return disposable
    }

    fun view(): V? {
        return view
    }
}