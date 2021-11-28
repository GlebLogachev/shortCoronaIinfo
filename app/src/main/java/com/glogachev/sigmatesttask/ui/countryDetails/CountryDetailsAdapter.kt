package com.glogachev.sigmatesttask.ui.countryDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glogachev.sigmatesttask.R
import com.glogachev.sigmatesttask.databinding.CountryDetailsItemBinding
import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class CountryDetailsAdapter() :
    ListAdapter<CoronaCountryDetailsDB, CountryDetailsViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailsViewHolder {
        return CountryDetailsViewHolder(
            binding = CountryDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CountryDetailsViewHolder(private val binding: CountryDetailsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
    fun bind(item: CoronaCountryDetailsDB) {
        binding.apply {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC)
            val localDateTime = LocalDateTime.parse(item.date, formatter)
            val date = localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd-LLLL"))
            tvDate.text = context.getString(R.string.date, date)
            tvStatus.text = context.getString(R.string.status, item.status)
            tvCases.text = context.getString(R.string.cases, item.cases)

        }
    }
}


private val diffUtil = object : DiffUtil.ItemCallback<CoronaCountryDetailsDB>() {
    override fun areItemsTheSame(
        oldItem: CoronaCountryDetailsDB,
        newItem: CoronaCountryDetailsDB
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CoronaCountryDetailsDB,
        newItem: CoronaCountryDetailsDB
    ): Boolean = oldItem == newItem
}