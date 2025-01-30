package com.empresa.aplicacion.data.repository

import com.empresa.aplicacion.data.network.ChucknorrisApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val api: ChucknorrisApi

    ){

      suspend fun getJoke() : String {
          return  withContext(IO){
              api.getRandomJoke().value
          }
      }


}