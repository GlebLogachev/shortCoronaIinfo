package com.glogachev.sigmatesttask.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.schedule() = scheduling(this)

private fun <T> scheduling(single: Single<T>): Single<T> {
    return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}