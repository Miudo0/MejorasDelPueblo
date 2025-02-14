package com.empresa.aplicacion.ui.ProblemasSugerencias.Seguridad

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.ProblemasSugerencias.MarcarProblemaResueltoViewModel
import com.empresa.aplicacion.ui.ProblemasSugerencias.ProblemasLista
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun SeguridadScreen(
    navigateTo: (String) -> Unit,
    navigateToLogin: () -> Unit

) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar(
                navigateToLogin = navigateToLogin,
            )
        },
        bottomBar = {
            AplicacionBottomAppBar(
                allScreens = destinosMejoras,
                onTabSelected = { ruta ->
                    navigateTo(ruta.route)
                },
                currentScreen = ProblemasSugerencias
            )
        }
    ) { paddingValues ->
        AppContent(
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun AppContent(paddingValues: PaddingValues) {

    val viewModel: MostrarProblemasSeguridadViewModel = hiltViewModel()
    val actualizarViewModel: MarcarProblemaResueltoViewModel = hiltViewModel()
    val deleteViewModel: DeleteProblemasSeguridadViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()


    when (val current = state) {
        is SeguridadState.Success -> {
            val problemas = current.problemas
            ProblemasLista(
                problemas = problemas,
                deleteProblema = {
                    deleteViewModel.deleteProblemasSeguridad(it)
                },
                paddingValues = paddingValues,
                marcarProblemaSolucionado = {
                    actualizarViewModel.marcarComoResuelto(it)
                }

            )

        }

        is SeguridadState.Error -> {
            Text(text = current.error)
        }

        is SeguridadState.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(paddingValues)
                .size(50.dp)
        )

    }
}

