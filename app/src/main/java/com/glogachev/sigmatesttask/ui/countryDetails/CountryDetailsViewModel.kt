package com.glogachev.sigmatesttask.ui.countryDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glogachev.sigmatesttask.domain.CoronaRepository
import com.glogachev.sigmatesttask.utils.schedule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class CountryDetailsViewModel(
    private val repository: CoronaRepository
) : ViewModel() {

    private val _state: MutableLiveData<CountryDetailsState> =
        MutableLiveData(CountryDetailsState.Loading)
    val state: LiveData<CountryDetailsState> get() = _state

    private val cd = CompositeDisposable()

    fun obtainCountryDetails(countryName: String) {
        repository
            .loadDetails(countryName)
            .schedule()
            .subscribe { list ->
                _state.value = CountryDetailsState.Success(list.sortedByDescending { it.date })
            }
            .addToCd()
    }

    override fun onCleared() {
        super.onCleared()
        cd.dispose()
    }

    private fun Disposable.addToCd() {
        cd.add(this)
    }
}

class CountryDetailsViewModelFactory(private val repository: CoronaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryDetailsViewModel(repository) as T
    }
}