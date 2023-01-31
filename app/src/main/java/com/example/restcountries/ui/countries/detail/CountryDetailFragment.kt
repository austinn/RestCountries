package com.example.restcountries.ui.countries.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    private val viewModel: CountryDetailViewModel by viewModels()

    private val args: CountryDetailFragmentArgs by navArgs()

    private var _binding: FragmentCountryDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set the name from safe-args
        binding.tvName.text = args.name

        // start listening for data only when the Lifecycle is STARTED
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getCountryDetails(name = args.name)

                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is CountryDetailUiState.Error -> {
                            // show error
                            Timber.w("CountryDetailUiState.Error")
                        }

                        is CountryDetailUiState.Loading -> {
                            // show loading
                            Timber.i("CountryDetailUiState.Loading")

                        }

                        is CountryDetailUiState.Success -> {
                            Timber.i("CountryDetailUiState.Success")
                            updateTextFields(uiState.country)
                        }
                    }
                }
            }
        }
    }

    private fun updateTextFields(country: CountryEntity) {
        country.capital?.let {
            binding.tvCapital.text = it
        }

        binding.tvPopulation.text = country.population.toString()
        binding.tvArea.text = country.area.toString()
        binding.tvRegion.text = country.region

        country.subregion?.let {
            binding.tvSubregion.text = it
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}