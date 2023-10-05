package com.alvarof18.marvelinfo

import android.app.Application
import com.alvarof18.marvelinfo.data.local.MarvelDatabase
import com.alvarof18.marvelinfo.data.local.MarvelDBInstance

class MarvelApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        MarvelDBInstance.InstanceDB =  MarvelDatabase.getDataBase(this)
    }

}