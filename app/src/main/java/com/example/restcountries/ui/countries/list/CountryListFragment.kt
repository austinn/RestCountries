package com.example.restcountries.ui.countries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.restcountries.data.local.Country
import com.example.restcountries.data.local.countryList
import com.example.restcountries.databinding.FragmentCountryListBinding

class CountryListFragment : Fragment() {

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

        val countryAdapter =
            CountryListAdapter(countryList, onClickCallback = object : CountryListCallback {
                override fun onClick(country: Country) {
                    Toast.makeText(context, country.name, Toast.LENGTH_SHORT).show()
                }
            })

        binding.rvCountries.apply {
            adapter = countryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}