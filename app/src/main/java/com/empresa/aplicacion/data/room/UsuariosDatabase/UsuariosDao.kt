package com.empresa.aplicacion.data.room.UsuariosDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuariosDao {

    //le meti suspend a todas
    @Query("SELECT * FROM Usuario")
    suspend fun getAll(): List<Usuario>;

    @Query("SELECT * FROM Usuario WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<Usuario>;

    @Query(
        "SELECT * FROM Usuario WHERE username LIKE :first AND " +
                "pass LIKE :last "
    )
    suspend fun findByName(first: String, last: String): Usuario

    @Query("SELECT username FROM Usuario WHERE username = :username AND pass = :pass")
    suspend fun getUserName(username: String, pass: String): String?

    @Insert
    suspend fun insertAll(vararg users: Usuario)

    @Delete
    suspend fun delete(user: Usuario)

}