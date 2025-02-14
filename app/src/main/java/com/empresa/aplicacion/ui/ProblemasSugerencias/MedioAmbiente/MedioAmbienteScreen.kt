package com.empresa.aplicacion.ui.ProblemasSugerencias.MedioAmbiente

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
import com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura.DeleteProblemasInfraestructuraViewModel
import com.empresa.aplicacion.ui.ProblemasSugerencias.Componentes.MarcarProblemaResueltoViewModel
import com.empresa.aplicacion.ui.ProblemasSugerencias.Componentes.ProblemasLista
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun MedioAmbienteScreen(
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
    val viewModel: MostrarProblemasMedioAmbienteViewModel = hiltViewModel()
    val actualizarViewModel: MarcarProblemaResueltoViewModel = hiltViewModel()

    val deleteViewModel: DeleteProblemasInfraestructuraViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    when (val current = state) {
        is MedioAmbienteState.Success -> {
            val problemas = current.problemas
            ProblemasLista(
                problemas = problemas,
                deleteProblema = {
                    deleteViewModel.deleteProblema(it)
                },
                paddingValues = paddingValues,
                marcarProblemaSolucionado = {
                    actualizarViewModel.marcarComoResuelto(it)
                }

            )

        }

        is MedioAmbienteState.Error -> {
            Text(text = current.error)
        }

        is MedioAmbienteState.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(paddingValues)
                .size(50.dp)
        )

    }

}
