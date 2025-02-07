package com.empresa.aplicacion.data.network

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.empresa.aplicacion.data.room.ChuckJokesDatabase.ChuckJokeDao
import com.empresa.aplicacion.data.room.ChuckJokesDatabase.ChuckJokesDatabase
import com.empresa.aplicacion.data.room.NotificacionesDatabase.NotificacionesDatabase
import com.empresa.aplicacion.data.room.ProblemasDatabase.ProblemasDatabase
import com.empresa.aplicacion.data.room.UsuariosDatabase.UsuariosDatabase
import com.empresa.aplicacion.ui.MejorasDelpueblo
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    //SharedPreferences
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }


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

    @Singleton
    @Provides
    fun providesGetDatabaseChuckJokes(@ApplicationContext context: Context): ChuckJokesDatabase =
        Room.databaseBuilder(
            context,
            ChuckJokesDatabase::class.java,
            "baseDatosChuckJokes"
        ).build()

    @Singleton
    @Provides
    fun providesChuckJokeDao(db: ChuckJokesDatabase): ChuckJokeDao = db.ChuckJokeDao()
}
