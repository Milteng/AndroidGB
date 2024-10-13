package ru.gb.android.homework3

import android.app.Application
import ru.gb.android.homework3.data.di.AppComponent
import ru.gb.android.homework3.data.di.DaggerAppComponent

class MarketSampleApp: Application() {

    /*init {
        instance = this
    }

    companion object {
        private var instance: MarketSampleApp? = null
        fun getInstance(): MarketSampleApp = instance!!
    }*/
   val appComponent:AppComponent = DaggerAppComponent.factory().create(this)
   //val appComponentPromo:AppComponentPromo = DaggerAppComponentPromo.factory().create(this)
}
