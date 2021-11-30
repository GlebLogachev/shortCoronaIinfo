package com.glogachev.sigmatesttask.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glogachev.sigmatesttask.data.db.models.CoronaSummaryDB
import io.reactivex.Single

@Dao
interface CoronaSummaryDAO {

    @Query("SELECT * FROM corona_summary")
    fun all(): Single<List<CoronaSummaryDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: List<CoronaSummaryDB>)

}