package com.glogachev.sigmatesttask.di.modules

import android.content.Context
import androidx.room.Room
import com.glogachev.sigmatesttask.db.AppDatabase
import com.glogachev.sigmatesttask.db.DATABASE
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE
        ).build()
    }
}