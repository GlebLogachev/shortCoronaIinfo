package com.glogachev.sigmatesttask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB
import io.reactivex.Single

@Dao
interface CoronaCountryDetailsDAO {

    @Query("SELECT * FROM corona_country_details")
    fun all(): Single<List<CoronaCountryDetailsDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: List<CoronaCountryDetailsDB>)

    @Query("SELECT * FROM corona_country_details WHERE country == :country")
    fun getCountryDetailsBy(country : String): Single<List<CoronaCountryDetailsDB>>

}