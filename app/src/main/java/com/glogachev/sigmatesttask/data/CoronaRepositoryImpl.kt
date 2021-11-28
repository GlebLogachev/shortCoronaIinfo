package com.glogachev.sigmatesttask.data

import com.glogachev.sigmatesttask.data.mappers.toDB
import com.glogachev.sigmatesttask.db.AppDatabase
import com.glogachev.sigmatesttask.db.models.CoronaCountryDetailsDB
import com.glogachev.sigmatesttask.db.models.CoronaSummaryDB
import com.glogachev.sigmatesttask.domain.CoronaRepository
import io.reactivex.Single
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class CoronaRepositoryImpl @Inject constructor(
    private val api: Api,
    private val database: AppDatabase
) : CoronaRepository {


    override fun loadCoronaInfo(): Single<List<CoronaSummaryDB>> {
        return api
            .getSummaryInfo()
            .flatMap {
                val countriesList: List<CoronaSummaryDB> = it.toDB()
                saveCoronaInfo(countriesList)
                Single.just(countriesList)
            }
            .onErrorResumeNext {
                database
                    .coronaSummary()
                    .all()
            }
    }

    private fun saveCoronaInfo(countriesList: List<CoronaSummaryDB>) {
        database
            .coronaSummary()
            .insert(countriesList)
    }

    override fun loadDetails(country: String): Single<List<CoronaCountryDetailsDB>> {
        val fromTo = fromToDateForRequest()
        return api
            .getCoronaDetails(country = country, from = fromTo.first, to = fromTo.second)
            .flatMap {
                val countryDetailsList = it.toDB()
                database
                    .coronaCountryDetails()
                    .insert(countryDetailsList)
                Single.just(countryDetailsList)
            }
            .onErrorResumeNext {
                database
                    .coronaCountryDetails()
                    .getCountryDetailsBy(country)
            }
    }

    private fun fromToDateForRequest(): Pair<String, String> {
        val formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd", Locale.US
        )

        val nowDate = LocalDateTime.now()
        val fromDate = nowDate.minusWeeks(2)
        val nowDateFormatted = nowDate.format(formatter)
        val fromDateFormatted = formatter.format(fromDate)
        return fromDateFormatted to nowDateFormatted
    }
}