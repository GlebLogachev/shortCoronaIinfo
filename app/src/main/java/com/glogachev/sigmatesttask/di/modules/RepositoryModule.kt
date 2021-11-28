package com.glogachev.sigmatesttask.di.modules

import com.glogachev.sigmatesttask.data.CoronaRepositoryImpl
import com.glogachev.sigmatesttask.domain.CoronaRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideCatRepository(repoImpl: CoronaRepositoryImpl): CoronaRepository
}