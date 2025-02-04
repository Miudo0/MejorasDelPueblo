package com.empresa.aplicacion.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.HomeScreen
import com.empresa.aplicacion.ui.InfraestructuraScreen
import com.empresa.aplicacion.ui.LoginScreen
import com.empresa.aplicacion.ui.ProblemasScreen
import com.empresa.aplicacion.ui.RegistroScreen
import com.empresa.aplicacion.ui.ReportarScreen
import com.empresa.aplicacion.ui.ValidarUSuarioViewModel


@Composable
fun NavigationWrapper() {

    val viewModel: ValidarUSuarioViewModel = hiltViewModel()

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        destinosMejoras.find { it.route == currentDestination?.route } ?: Home

    NavHost(navController = navController, startDestination = Login.route) {

        //navegacion desde el login

        composable(Login.route) {
            LoginScreen(viewModel = viewModel, navigateTo = { ruta ->
                navController.navigate(ruta)

            }
            )
        }
        composable(Home.route) {

            HomeScreen(viewModel = viewModel, navigateTo = { ruta ->
                Log.d("NAVIGATE_TO", "Screen recibida: $ruta")
                when (ruta) {
                    ProblemasSugerencias.route -> navController.navigate(ProblemasSugerencias.route)

                }

            })
        }

        //navegacion desde problemas y sugerencias
        composable(route = ProblemasSugerencias.route) {
            ProblemasScreen(
                viewModel = viewModel,
                onTabSelected = { ruta ->
                    navController.navigate(ruta.route)
                },
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                }


                )
        }
//
        //navegacion desde el registro
        composable(Registro.route) {
            RegistroScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
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



        composable(Titulo.route) {

            AplicacionTopAppBar(
                viewModel = viewModel
            )
        }

        composable(ReportarProblema.route) {
            ReportarScreen(
                navigateTo = { ruta ->
                    Log.d("navengado", "Screen recibida: $ruta")
                    navController.navigate(ruta)
                }
            )
        }
       //pantallas de los problems y sugerencias

        composable(route = Infraestructura.route) {
            InfraestructuraScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },
                viewModel = viewModel
            )
        }

    }
}
