package com.glogachev.sigmatesttask.data

import com.glogachev.sigmatesttask.data.model.CoronaDetailsNW
import com.glogachev.sigmatesttask.data.model.CoronaSummaryNW
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("summary")
    fun getSummaryInfo(): Single<CoronaSummaryNW>

    @GET("country/{country}/status/deaths")
    fun getCoronaDetails(
        @Path("country") country: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): Single<CoronaDetailsNW>
}


