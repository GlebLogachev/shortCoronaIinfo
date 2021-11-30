package com.glogachev.sigmatesttask.data.mappers

import com.glogachev.sigmatesttask.data.model.CoronaDetailsNW
import com.glogachev.sigmatesttask.data.model.CoronaSummaryNW
import com.glogachev.sigmatesttask.data.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB

fun CoronaSummaryNW.toDB(): List<CoronaSummaryDB> {
    return this.countriesNW.map {
        CoronaSummaryDB(
            country = it.country,
            totalConfirmed = it.totalConfirmed,
            totalDeaths = it.totalDeaths,
            totalRecovered = it.totalRecovered
        )
    }
}

fun CoronaDetailsNW.toDB(): List<CoronaCountryDetailsDB> {
    return this.map {
        CoronaCountryDetailsDB(
            country = it.country,
            status = it.status,
            date = it.date,
            cases = it.cases
        )
    }
}