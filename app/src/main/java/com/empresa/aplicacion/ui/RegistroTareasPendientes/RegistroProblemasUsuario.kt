package com.empresa.aplicacion.ui.RegistroTareasPendientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.domain.Problema
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.navigation.RegistroUsuario
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun RegistroProblemasScreen(

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
                currentScreen = RegistroUsuario
            )
        }
    ) { paddingValues ->
        AppContent(

            paddingValues = paddingValues
        )
    }
}

@Composable
fun AppContent(

    paddingValues: PaddingValues,
    viewModel: RegistroViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (val current = state) {
        is RegistroState.Success -> {
            val problemas = current.problemas


            ProblemasListaUsuario(
                problemas = problemas,
                paddingValues = paddingValues,

                )
        }

        is RegistroState.Error -> {
            Text(text = current.error)
        }

        is RegistroState.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(paddingValues)
                .size(50.dp)
        )
    }
}

@Composable
private fun ProblemasListaUsuario(
    problemas: List<Problema>,

    paddingValues: PaddingValues,


    ) {
    if (problemas.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay problemas registrados.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }else{

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(problemas) { problema ->
                    CartaItemUsuario(
                        problema
                    )
                }
            }
        }
}

@Composable
private fun CartaItemUsuario(
    problema: Problema,


    ) {

    val colorSuperior = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
    val colorInferior = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (problema.resuelto) {
                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            }
        )
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Box para username, titulo y descripcion
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorSuperior, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado más pequeño
                ) {

                    problema.titulo?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    problema.descripcion?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Left
                        )
                    }
                    if (problema.resuelto) {
                        Text(
                            text = "Problema solucionado, esperando por otra confirmacion",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}