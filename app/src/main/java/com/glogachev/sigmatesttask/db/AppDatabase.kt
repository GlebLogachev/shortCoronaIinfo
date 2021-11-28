package com.glogachev.sigmatesttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glogachev.sigmatesttask.db.dao.CoronaCountryDetailsDAO
import com.glogachev.sigmatesttask.db.dao.CoronaSummaryDAO
import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.db.models.CoronaSummaryDB

@Database(entities = [CoronaSummaryDB::class, CoronaCountryDetailsDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coronaSummary(): CoronaSummaryDAO
    abstract fun coronaCountryDetails() : CoronaCountryDetailsDAO
}

const val DATABASE = "database"