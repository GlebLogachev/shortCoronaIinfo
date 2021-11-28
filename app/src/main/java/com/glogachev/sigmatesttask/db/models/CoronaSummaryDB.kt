package com.glogachev.sigmatesttask.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "corona_summary")
data class CoronaSummaryDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val country: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)
