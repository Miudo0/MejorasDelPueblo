package com.empresa.aplicacion.data.SqlLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log


class UsuariosRegistradosDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        Log.d("SqlLite", "Se ha creado la base de datos")
        db.execSQL(SQL_INICIAL_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "UsuariosRegistrados.db";
        private const val DATABASE_VERSION = 1
    }
}

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_USERNAME} TEXT," +
            "${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.COLUMN_NAME_PASS} TEXT)"

private const val SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS ${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.TABLE_NAME}"

const val SQL_INICIAL_USER = """
INSERT INTO ${UsuariosRegistradosSqliteContrato.UsuariosRegistradosEntry.TABLE_NAME}
VALUES (1,'Admin', '1234')
"""