package com.empresa.aplicacion.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.Home.HomeScreen
import com.empresa.aplicacion.ui.Login.LoginScreen
import com.empresa.aplicacion.ui.Login.RegistroScreen
import com.empresa.aplicacion.ui.Notificaciones.NotificationsScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura.InfraestructuraScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.MedioAmbiente.MedioAmbienteScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.ProblemasScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.Reportar.ReportarScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.Seguridad.SeguridadScreen
import com.empresa.aplicacion.ui.ProblemasSugerencias.Trafico.TraficoScreen


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
            LoginScreen(
                navigateToHome = { navController.navigate(Home.route) },
                navigateToLogin = { navController.navigate(Login.route) },
                navigateToRegistro = { navController.navigate(Registro.route) },
            )
        }


        //navegacion desde el homeScreen
        composable(Home.route) {
            HomeScreen(

                navigateToProblemas = { navController.navigate(ProblemasSugerencias.route) },
                navigateToNotificaicones = { navController.navigate(NotificacionesScreen.route) },
                navigateToVoluntariado = { navController.navigate(Voluntariado.route) },
                navigateToProyectos = { navController.navigate(Proyectos.route) },
                navigateToLogin = { navController.navigate(Login.route) },
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }
        //navegacion desde problemas y sugerencias
        composable(route = ProblemasSugerencias.route) {
            ProblemasScreen(
                onTabSelected = { ruta ->
                    navController.navigate(ruta.route)
                },
                navigateToLogin = { navController.navigate(Login.route) },
                navigateToReportarProblema = { navController.navigate(ReportarProblema.route) },
                navigateToOpcion = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }


        composable(route = NotificacionesScreen.route) {
            NotificationsScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }


        //navegacion desde el registro
        composable(Registro.route) {
            RegistroScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }
        //navegaicon del bottom bar
        composable(BotonesAcceso.route) {
            AplicacionBottomAppBar(
                allScreens = destinosMejoras,
                onTabSelected = { ruta ->
                    navController.navigate(ruta.route)
                },
                currentScreen = currentScreen,

                )
        }
        //appBar
        composable(Titulo.route) {
            AplicacionTopAppBar(
                navigateToLogin = {
                    navController.navigate(Login.route)
                    Log.d("logout", "vamos para el login")
                },
            )
        }


        //pantallas de los problems y sugerencias
        composable(ReportarProblema.route) {
            ReportarScreen(
                navigateTo = { ruta ->
                    Log.d("navegado", "Screen recibida: $ruta")
                    navController.navigate(ruta)
                }
            )
        }
        composable(route = Infraestructura.route) {
            InfraestructuraScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },
                navigateToLogin = {
                    navController.navigate(Login.route)
                }


                )
        }
        composable(route = Trafico.route) {
            TraficoScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },

                )
        }
        composable(route = MedioAmbiente.route) {
            MedioAmbienteScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },

                )
        }
        composable(route = Seguridad.route) {
            SeguridadScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },

                )
        }


    }
}
