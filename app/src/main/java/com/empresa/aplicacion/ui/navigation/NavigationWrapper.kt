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
import com.empresa.aplicacion.ui.home.HomeScreen
import com.empresa.aplicacion.ui.login.LoginScreen
import com.empresa.aplicacion.ui.login.RegistroScreen
import com.empresa.aplicacion.ui.problemasSugerencias.infraestructura.InfraestructuraScreen
import com.empresa.aplicacion.ui.problemasSugerencias.medioAmbiente.MedioAmbienteScreen
import com.empresa.aplicacion.ui.problemasSugerencias.ProblemasScreen
import com.empresa.aplicacion.ui.problemasSugerencias.reportar.ReportarScreen
import com.empresa.aplicacion.ui.problemasSugerencias.seguridad.SeguridadScreen
import com.empresa.aplicacion.ui.problemasSugerencias.trafico.TraficoScreen
import com.empresa.aplicacion.ui.registroTareasPendientes.RegistroProblemasScreen


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
                navigateToRegistro = { navController.navigate(RegistroUsuario.route) },

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


        composable(route = RegistroUsuario.route) {
            RegistroProblemasScreen (
                navigateTo = { ruta ->
                    navController.navigate(ruta)

                    },
                navigateToLogin = {
                    navController.navigate(Login.route)
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
                navigateToProblemasSugerencias = {
                    navController.navigate(ProblemasSugerencias.route)
                },
                navigateTo = { ruta ->
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
                navigateToLogin = {
                    navController.navigate(Login.route)
                }

            )
        }
        composable(route = MedioAmbiente.route) {
            MedioAmbienteScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },
                navigateToLogin = {
                    navController.navigate(Login.route)
                }
            )
        }
        composable(route = Seguridad.route) {
            SeguridadScreen(
                navigateTo = { ruta ->
                    navController.navigate(ruta)
                },
                navigateToLogin = {
                    navController.navigate(Login.route)
                }

            )
        }


    }
}
