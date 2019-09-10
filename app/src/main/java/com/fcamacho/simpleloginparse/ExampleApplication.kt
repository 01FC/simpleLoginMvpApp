package com.fcamacho.simpleloginparse

import android.app.Application
import com.parse.Parse

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("appId")
                .server("http://159.203.102.80:1337/parse/")
                .build()
        )
    }
}