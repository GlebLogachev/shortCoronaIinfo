package com.glogachev.sigmatesttask.ui.countryDetails

import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB

sealed class CountryDetailsState {
    data class Success(
        val initialList: List<CoronaCountryDetailsDB> = emptyList(),
    ) : CountryDetailsState()

    object Loading : CountryDetailsState()
}