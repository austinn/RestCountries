package com.example.restcountries.ui.countries.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.data.repositories.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountryDetailUiState>(CountryDetailUiState.Loading())
    val uiState: StateFlow<CountryDetailUiState> = _uiState

    fun getCountryDetails(name: String) {
        viewModelScope.launch {
            repository.getCountryDetails(name).collect { country ->
                if (country == null) {
                    _uiState.update { CountryDetailUiState.Error(NullPointerException()) }
                } else {
                    _uiState.update { CountryDetailUiState.Success(country) }
                }
            }
        }
    }
}

sealed class CountryDetailUiState {
    data class Success(val country: CountryEntity) : CountryDetailUiState()
    data class Loading(val loading: Boolean = true) : CountryDetailUiState()
    data class Error(val exception: Throwable? = null) : CountryDetailUiState()
}