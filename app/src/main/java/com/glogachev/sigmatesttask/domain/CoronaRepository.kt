package com.glogachev.sigmatesttask.domain

import com.glogachev.sigmatesttask.data.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface CoronaRepository {

    fun updateCoronaInfo(): Completable
    fun loadDetails(country: String): Single<List<CoronaCountryDetailsDB>>
    fun getCoronaListObservable(): Observable<List<CoronaSummaryDB>>
}