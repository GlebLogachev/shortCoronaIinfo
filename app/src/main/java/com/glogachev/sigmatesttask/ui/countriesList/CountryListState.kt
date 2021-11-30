package com.glogachev.sigmatesttask.ui.countriesList

import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB

sealed class CountryListState {
    data class Success(
        val initialList: List<CoronaSummaryDB> = emptyList(),
        val searchValue: String = ""
    ) : CountryListState() {
        val currentList: List<CoronaSummaryDB> =
            initialList.filter { it.country.contains(searchValue, true) }
    }

    object Loading : CountryListState()
}