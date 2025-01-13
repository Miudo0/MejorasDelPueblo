package com.empresa.aplicacion.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuariosDao {
    @Query("SELECT * FROM Usuario")
    fun getAll(): List<Usuario>;

    @Query("SELECT * FROM Usuario WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Usuario>;

    @Query("SELECT * FROM Usuario WHERE username LIKE :first AND " +
   "pass LIKE :last ")
    fun findByName(first: String, last: String): Usuario

    @Insert
    fun insertAll(vararg users: Usuario)

    @Delete
    fun delete(user: Usuario)

}