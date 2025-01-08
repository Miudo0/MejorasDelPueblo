package com.empresa.aplicacion.ui.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.empresa.aplicacion.ui.ui.HomeScreen
import com.empresa.aplicacion.ui.ui.ProblemasScreen
import com.empresa.aplicacion.ui.ui.LoginScreen


@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {


        composable<Login> {
            LoginScreen {
                navController.navigate(Home)
            }
        }

        composable<Home> {
            HomeScreen { numero ->
                Log.d("NAVIGATE_TO", "Numero recibido: $numero")
                when (numero) {
                    1 -> navController.navigate(ReporteProblemas)

                }
            }
        }
        composable<ReporteProblemas> {
           ProblemasScreen()
        }


    }
}