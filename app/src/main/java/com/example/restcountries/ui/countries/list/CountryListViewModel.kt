package com.example.restcountries.ui.countries.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.data.repositories.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryListUiState>(CountryListUiState.Loading())
    val uiState: StateFlow<CountryListUiState> = _uiState

    init {
        getCountries()
    }

    fun getCountries() {

        viewModelScope.launch {
            repository.getCountryList().collect { countries ->
                _uiState.update { CountryListUiState.Success(countries) }
            }
        }
    }
}

sealed class CountryListUiState {
    data class Success(val countries: List<CountryEntity>) : CountryListUiState()
    data class Loading(val loading: Boolean = true) : CountryListUiState()
    data class Error(val exception: Throwable? = null) : CountryListUiState()
}