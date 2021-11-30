package com.glogachev.sigmatesttask.ui.countriesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB
import com.glogachev.sigmatesttask.domain.CoronaRepository
import com.glogachev.sigmatesttask.utils.schedule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CountryListViewModel(
    private val repository: CoronaRepository
) : ViewModel() {

    private val cd = CompositeDisposable()

    init {
        obtainCountriesList()
    }

    private val _state: MutableLiveData<CountryListState> =
        MutableLiveData(CountryListState.Loading)
    val state: LiveData<CountryListState>
        get() = _state

    private fun obtainCountriesList() {
        repository
            .loadCoronaInfo()
            .schedule()
            .subscribe { list ->
                setSuccessState(list)
            }
            .addToCd()
    }

    private fun setSuccessState(list: List<CoronaSummaryDB>) {
        _state.value = CountryListState.Success(
            initialList = list
        )
    }

    fun refresh() {
        obtainCountriesList()
    }

    override fun onCleared() {
        super.onCleared()
        cd.dispose()
    }

    private fun Disposable.addToCd() {
        cd.add(this)
    }

    fun newSearchValue(newText: String) {
        val state = _state.value as CountryListState.Success
        _state.value = state.copy(
            searchValue = newText
        )
    }
}


class CountryListViewModelFactory(private val repository: CoronaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryListViewModel(repository) as T
    }
}