package com.glogachev.sigmatesttask.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "corona_country_details")
data class CoronaCountryDetailsDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val country: String,
    val cases : Int,
    val status: String,
    val date: String,
)