package com.empresa.aplicacion.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.HomeScreen
import com.empresa.aplicacion.ui.LoginScreen
import com.empresa.aplicacion.ui.ProblemasScreen
import com.empresa.aplicacion.ui.RegistroScreen


@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        destinosMejoras.find { it.route == currentDestination?.route } ?: Home

    NavHost(navController = navController, startDestination = Login.route) {

        //navegacion desde el login


        composable(Login.route) {
            LoginScreen { ruta ->
                navController
                    .navigate(ruta)
            }


        }
        composable(route = Home.route) {
            HomeScreen { ruta ->
                Log.d("NAVIGATE_TO", "Screen recibida: $ruta")
                when (ruta) {
                    ProblemasSugerencias.route -> navController.navigate(ProblemasSugerencias.route)

                }
            }

        }

        //navegacion desde problemas y sugerencias
        composable(route = ProblemasSugerencias.route) {
            ProblemasScreen(
                onTabSelected = { ruta ->
                    navController.navigate(ruta.route)
                },

                )
        }
//
//        //navegacion desde el registro
        composable(Registro.route) {
            RegistroScreen { navController.navigate(Home.route) }
        }


        composable(BotonesAcceso.route) {
            AplicacionBottomAppBar(
                allScreens = destinosMejoras,
                onTabSelected = { ruta ->
                    navController.navigate(ruta.route)
                },
                currentScreen = currentScreen,

                )
        }

    }
}
//esto no me funciona
//para que no haga duplicados al navegar. copiado de codelab Rally
//fun NavHostController.navigateSingleTopTo(route: String) =
//    this.navigate(route) {
//
//        popUpTo(
//            this@navigateSingleTopTo.graph.findStartDestination().id
//        )
//        {
//            saveState = true
//        }
//        launchSingleTop = true
//        restoreState = true
//    }