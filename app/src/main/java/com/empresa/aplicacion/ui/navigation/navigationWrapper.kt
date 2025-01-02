package com.empresa.aplicacion.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.empresa.aplicacion.ui.HomeScreen
import com.empresa.aplicacion.ui.EnConstruccion


@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen { numero ->
                Log.d("NAVIGATE_TO", "Numero recibido: $numero")
                when (numero) {
                    1 -> navController.navigate(EnConstruccion)

                }
            }
        }
        composable<EnConstruccion> {
            EnConstruccion()
        }



    }
}