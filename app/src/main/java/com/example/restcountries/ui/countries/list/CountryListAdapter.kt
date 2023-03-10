package com.example.restcountries.ui.countries.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.restcountries.R
import com.example.restcountries.data.entities.CountryEntity
import com.example.restcountries.databinding.ListItemCountryBinding

interface CountryListCallback {
    fun onClick(country: CountryEntity)
}

class CountryListAdapter(
    private var list: List<CountryEntity> = ArrayList(),
    private val onClickCallback: CountryListCallback,
) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun setData(countries: List<CountryEntity>) {
        list = countries
        notifyItemRangeChanged(0, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)

        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.country_list_anim)
    }

    override fun getItemCount() = list.size

    inner class CountryViewHolder(private val binding: ListItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryEntity) {
            binding.tvCountryName.text = country.name

            Glide.with(itemView.context)
                .load(country.flag)
                .transform(RoundedCorners(16))
                .into(binding.ivCountryFlag)

            binding.cardCountry.setOnClickListener { onClickCallback.onClick(country) }
        }
    }
}