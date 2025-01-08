package com.empresa.aplicacion.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuariosDao {
    @Query("SELECT * FROM Usuario")
    fun getAll(): List<Usuarios.Usuario>;

    @Query("SELECT * FROM Usuario WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Usuarios.Usuario>;

    @Query("SELECT * FROM Usuario WHERE username LIKE :first AND " +
   "pass LIKE :last ")
    fun findByName(first: String, last: String): Usuarios.Usuario

    @Insert
    fun insertAll(vararg users: Usuarios.Usuario)

    @Delete
    fun delete(user: Usuarios.Usuario)

}