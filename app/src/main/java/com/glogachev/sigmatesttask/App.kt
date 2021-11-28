package com.glogachev.sigmatesttask

import android.app.Application
import com.glogachev.sigmatesttask.di.AppComponent
import com.glogachev.sigmatesttask.di.DaggerAppComponent
import com.glogachev.sigmatesttask.di.modules.AppModule

class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(appModule = AppModule(applicationContext))

    }
}