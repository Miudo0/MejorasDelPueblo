package com.empresa.aplicacion.ui

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.empresa.aplicacion.data.sqlLite.UsuariosRegistradosDbHelper
import com.empresa.aplicacion.data.sqlLite.UsuariosRegistradosSqliteContrato
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MejorasDelpueblo : Application() {
    val TAG = "Mejoras"


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: La aplicación se ha creado.")
        appContext = applicationContext
       incializarDB()
    }
    //obtener el contexto glogal
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "onTerminate: La aplicación se ha terminado.")
        cerrarDB()
    }


    var UsuarioDb: SQLiteDatabase? = null
    private fun incializarDB() {

        val dbHelper = UsuariosRegistradosDbHelper(this)
        UsuarioDb = dbHelper.writableDatabase
        Log.d("SqlLite","Se ha inicializado la base de datos")
    }

    private fun cerrarDB() {
        UsuarioDb?.close()
        Log.d("SqlLite", "Base de datos cerrada")
    }

    fun leerDatos() {

        val dbHelper = UsuarioDb ?: return//si no hay base sale

        val projection = arrayOf(
            BaseColumns._ID,
            UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_USERNAME,
            UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_PASS
        )

        val cursor = dbHelper.query(
            UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.TABLE_NAME, projection,
            null, null, null, null, null
        )
        with(cursor) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
                val username =
                    cursor.getString(cursor.getColumnIndexOrThrow(UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_USERNAME))
                val pass =
                    cursor.getString(cursor.getColumnIndexOrThrow(UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_PASS))
                Log.d("SqlLite", "ID: $id, Username: $username, Pass: $pass")
            }
        }
        cursor.close()
    }


    fun borrarDB() {

        deleteDatabase(UsuariosRegistradosDbHelper.DATABASE_NAME)
        Log.d("SqlLite", "Base de datos borrada")
    }

    //Suponiendo que el usuario es unico
    fun actualizarPass(username: String, newPass: String) {
        val db = UsuarioDb ?: return

        val contentValues = ContentValues().apply {
            put(
                UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_PASS,
                newPass
            )
        }

        val seleccion =
            "${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_USERNAME} = ?"
        val seleccionArg = arrayOf(username)

        val filaAfectada = db.update(
            UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.TABLE_NAME,
            contentValues,
            seleccion,
            seleccionArg,

            )

        if (filaAfectada > 0) {
            Log.d("SqlLite", "Contraseña actualizada con éxito para el usuario: $username")
        } else {
            Log.d(
                "SqlLite",
                "No se encontró el usuario con el nombre: $username o no se pudo actualizar."
            )
        }
    }


}


