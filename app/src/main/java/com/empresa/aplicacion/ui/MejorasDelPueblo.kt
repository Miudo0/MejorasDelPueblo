package com.empresa.aplicacion.ui

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.empresa.aplicacion.data.UsuariosRegistradosDbHelper
import com.empresa.aplicacion.data.UsuariosRegistradosSqliteContrato

class MejorasDelpueblo : Application() {
    val TAG = "Mejoras"




    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: La aplicación se ha creado.")
        crearDB()
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "onTerminate: La aplicación se ha terminado.")
        cerrarDB()

    }



    var UsuarioDb: SQLiteDatabase? = null
    private fun crearDB() {

        val dbHelper = UsuariosRegistradosDbHelper(this)
        UsuarioDb = dbHelper.writableDatabase
    }

   private fun cerrarDB() {
        UsuarioDb?.close()
        Log.d("SqlLite", "Base de datos cerrada")
    }
}


