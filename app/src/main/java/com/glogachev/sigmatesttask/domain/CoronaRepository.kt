package com.glogachev.sigmatesttask.domain

import com.glogachev.sigmatesttask.data.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB
import io.reactivex.Single

interface CoronaRepository {

    fun loadCoronaInfo(): Single<List<CoronaSummaryDB>>
    fun loadDetails(country : String) : Single<List<CoronaCountryDetailsDB>>
}