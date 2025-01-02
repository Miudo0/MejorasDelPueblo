package com.empresa.aplicacion.ui

import android.app.Application
import android.util.Log

class MejorasDelpueblo : Application() {
    val TAG = "Mejoras"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: La aplicación se ha creado.")

    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "onTerminate: La aplicación se ha terminado.")
    }

}


