package com.empresa.aplicacion.data.SqlLite

import android.provider.BaseColumns

object UsuariosRegistradosSqliteContrato {
    object UsuariosRegistradosEntry : BaseColumns {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_NAME_USERNAME = "username"
        const val COLUMN_NAME_PASS = "pass"

    }

}
