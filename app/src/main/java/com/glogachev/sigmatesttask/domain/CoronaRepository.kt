package com.glogachev.sigmatesttask.domain

import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.db.models.CoronaSummaryDB
import io.reactivex.Completable
import io.reactivex.Single

interface CoronaRepository {

    fun loadCoronaInfo(): Single<List<CoronaSummaryDB>>
    fun loadDetails(country : String) : Single<List<CoronaCountryDetailsDB>>
}