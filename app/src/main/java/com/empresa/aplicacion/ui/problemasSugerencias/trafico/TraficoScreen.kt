package com.empresa.aplicacion.ui.problemasSugerencias.trafico

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.problemasSugerencias.componentes.MarcarProblemaResueltoViewModel
import com.empresa.aplicacion.ui.problemasSugerencias.componentes.ProblemasLista
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
            if (problemas.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.boladesierto),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .graphicsLayer(alpha = 0.8f) // Opacidad para mejorar legibilidad
                    )

                    Text(
                        text = "No hay problemas registrados. Comienza a colaborar!!!",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp // Espaciado entre letras
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.BottomCenter)
                            .offset(y = (-40).dp) // Desplazamiento del texto hacia arriba
                    )
                }
            } else {
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

