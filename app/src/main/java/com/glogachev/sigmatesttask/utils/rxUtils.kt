package com.glogachev.sigmatesttask.utils

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.schedule() = scheduling(this)
fun <T> Observable<T>.schedule() = scheduling(this)
fun Completable.schedule() = scheduling(this)

private fun <T> scheduling(single: Single<T>): Single<T> {
    return single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

private fun scheduling(completable: Completable): Completable {
    return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> scheduling(observable: Observable<T>): Observable<T> {
    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}