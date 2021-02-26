package com.sarafaria.android.github.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Shortcut of Schedulers.io()
 * @return the result of Schedulers.io()
 * @see Schedulers.io()
 */
fun ioThread(): Scheduler{
    return Schedulers.io()
}

/**
 * Shortcut of Schedulers.newThread()
 * @return the result of Schedulers.newThread()
 * @see Schedulers.newThread()
 */
fun newThread(): Scheduler{
    return Schedulers.newThread()
}

/**
 * Shortcut of AndroidSchedulers.mainThread()
 * @return the result of AndroidSchedulers.mainThread()
 * @see AndroidSchedulers.mainThread()
 */
fun androidThread(): Scheduler {
    return AndroidSchedulers.mainThread()
}