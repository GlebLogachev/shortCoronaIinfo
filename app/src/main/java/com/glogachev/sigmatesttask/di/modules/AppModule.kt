package com.glogachev.sigmatesttask.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context : Context) {

    @Provides
    fun provideContext() : Context = context
}