package com.glogachev.sigmatesttask.ui.countriesList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glogachev.sigmatesttask.R
import com.glogachev.sigmatesttask.databinding.CountryItemBinding
import com.glogachev.sigmatesttask.db.models.CoronaSummaryDB

class CountriesAdapter(
    private val onCountryCLick: (String) -> Unit
) : ListAdapter<CoronaSummaryDB, CountryListViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        return CountryListViewHolder(
            binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(getItem(position), onCountryCLick)
    }
}

class CountryListViewHolder(private val binding: CountryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
    fun bind(item: CoronaSummaryDB, onCountryCLick: (String) -> Unit) {
        binding.root.setOnClickListener {
            onCountryCLick(item.country)
        }
        binding.apply {
            tvCountryName.text = context.getString(R.string.country_name, item.country)
            tvTotalConfirmed.text = context.getString(R.string.confirmed, item.totalConfirmed)
            tvTotalDeaths.text = context.getString(R.string.deaths, item.totalDeaths)
            tvTotalRecovered.text = context.getString(R.string.recovered, item.totalRecovered)
        }
    }
}


private val diffUtil = object : DiffUtil.ItemCallback<CoronaSummaryDB>() {
    override fun areItemsTheSame(oldItem: CoronaSummaryDB, newItem: CoronaSummaryDB): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CoronaSummaryDB, newItem: CoronaSummaryDB): Boolean =
        oldItem == newItem
}