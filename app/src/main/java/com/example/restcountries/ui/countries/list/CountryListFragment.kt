package com.example.restcountries.ui.countries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.databinding.FragmentCountryListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private val viewModel: CountryListViewModel by viewModels()

    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup recyclerview and adapter
        val countryAdapter =
            CountryListAdapter(emptyList(), onClickCallback = object : CountryListCallback {
                override fun onClick(country: CountryEntity) {
                    val action =
                        CountryListFragmentDirections.actionNavigationCountriesToCountryDetailFragment(
                            name = country.name
                        )
                    findNavController().navigate(action)
                }
            })



        binding.rvCountries.apply {
            adapter = countryAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.getCountries()
        }

        // start listening for data only when the Lifecycle is STARTED
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is CountryListUiState.Error -> {
                            // show error
                            Timber.w("CountryListUiState.Error")
                            binding.refreshLayout.isRefreshing = false
                        }

                        is CountryListUiState.Loading -> {
                            // show loading
                            Timber.i("CountryListUiState.Loading")
                            binding.refreshLayout.isRefreshing = true
                        }

                        is CountryListUiState.Success -> {
                            Timber.i("CountryListUiState.Success")
                            countryAdapter.setData(uiState.countries)
                            binding.refreshLayout.isRefreshing = false
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}