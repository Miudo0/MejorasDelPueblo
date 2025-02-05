package com.empresa.aplicacion.data.network

import androidx.room.Room
import com.empresa.aplicacion.data.room.NotificacionesDatabase.NotificacionesDatabase
import com.empresa.aplicacion.data.room.ProblemasDatabase.ProblemasDatabase
import com.empresa.aplicacion.data.room.UsuariosDatabase.UsuariosDatabase
import com.empresa.aplicacion.ui.MejorasDelpueblo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //api de chuck norris
    @Singleton
    @Provides
    fun providesJson() = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun providesChuckNorrisAPI(json: Json): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @Singleton
    @Provides
    fun providesChuckNorrisService(retrofit: Retrofit): ChucknorrisApi =
        retrofit.create(ChucknorrisApi::class.java)

    //bases de datos
    @Singleton
    @Provides
    //@applicationcontext context:context
    fun providesGetDatabase() =
        Room.databaseBuilder(
            MejorasDelpueblo.appContext,
            UsuariosDatabase::class.java,
            "baseDatosMejoras"
        ).build()

    @Singleton
    @Provides
    fun providesGetDatabaseProblemas() =
        Room.databaseBuilder(
            MejorasDelpueblo.appContext,
            ProblemasDatabase::class.java,
            "baseDatosProblemas"

        ).build()

    @Singleton
    @Provides
    fun providesGetDatabaseNotificaciones() =
        Room.databaseBuilder(
            MejorasDelpueblo.appContext,
            NotificacionesDatabase::class.java,
            "baseDatosNotificaciones"
        ).build()


}
