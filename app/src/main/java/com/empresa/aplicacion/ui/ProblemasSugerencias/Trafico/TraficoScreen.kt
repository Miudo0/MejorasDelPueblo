package com.empresa.aplicacion.ui.ProblemasSugerencias.Trafico

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.ProblemasSugerencias.Componentes.MarcarProblemaResueltoViewModel
import com.empresa.aplicacion.ui.ProblemasSugerencias.Componentes.ProblemasLista
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun TraficoScreen(
    navigateTo: (String) -> Unit,
    navigateToLogin: () -> Unit

) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar(navigateToLogin = navigateToLogin)
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

    val viewModel: MostrarProblemasTraficoViewModel = hiltViewModel()
    val deleteViewModel: DeleteProblemasTraficoViewModel = hiltViewModel()
    val actualizarViewModel: MarcarProblemaResueltoViewModel = hiltViewModel()


    val state by viewModel.state.collectAsState()

    when (val current = state) {
        is TraficoState.Success -> {
            val problemas = current.problemas

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Problemas de Trafico",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()

                )
            }
            Column(modifier = Modifier.padding(top = 40.dp)) {


                ProblemasLista(
                    problemas = problemas,
                    deleteProblema = {
                        deleteViewModel.deleteProblemasTrafico(it)
                    },
                    paddingValues = paddingValues,
                    marcarProblemaSolucionado = {
                        actualizarViewModel.marcarComoResuelto(it)
                    }

                )
            }
        }

        is TraficoState.Error -> {
            Text(text = current.error)
        }

        is TraficoState.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(paddingValues)
                .size(50.dp)
        )

    }

}

