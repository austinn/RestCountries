package com.example.restcountries.ui.countries.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restcountries.data.local.Country
import com.example.restcountries.databinding.ListItemCountryBinding

interface CountryListCallback {
    fun onClick(country: Country)
}

class CountryListAdapter(
    private var list: List<Country> = ArrayList(),
    private val onClickCallback: CountryListCallback,
) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun setData(countries: List<Country>) {
        list = countries
        notifyItemRangeInserted(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
        holder.itemView.setOnClickListener { onClickCallback.onClick(country) }
    }

    override fun getItemCount() = list.size

    inner class CountryViewHolder(private val binding: ListItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.tvCountryName.text = country.name
        }
    }
}