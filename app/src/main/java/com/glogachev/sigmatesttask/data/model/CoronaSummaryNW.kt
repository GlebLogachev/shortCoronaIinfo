package com.glogachev.sigmatesttask.data.model


import com.google.gson.annotations.SerializedName

data class CoronaSummaryNW(
    @SerializedName("Countries")
    val countriesNW: List<CountryNW>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("Message")
    val message: String
)

data class CountryNW(
    @SerializedName("Country")
    val country: String,
    @SerializedName("CountryCode")
    val countryCode: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("NewConfirmed")
    val newConfirmed: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("Slug")
    val slug: String,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int
)