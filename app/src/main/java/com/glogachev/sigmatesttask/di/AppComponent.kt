package com.glogachev.sigmatesttask.di

import com.glogachev.sigmatesttask.di.modules.AppModule
import com.glogachev.sigmatesttask.di.modules.DatabaseModule
import com.glogachev.sigmatesttask.di.modules.NetworkModule
import com.glogachev.sigmatesttask.di.modules.RepositoryModule
import com.glogachev.sigmatesttask.ui.MainActivity
import com.glogachev.sigmatesttask.ui.countriesList.CountryListFragment
import com.glogachev.sigmatesttask.ui.countryDetails.CountryDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class, NetworkModule::class, RepositoryModule::class, AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Builder {
        fun create(appModule: AppModule): AppComponent
    }

    fun inject(target: MainActivity)
    fun inject(target: CountryListFragment)
    fun inject(target : CountryDetailsFragment)
}