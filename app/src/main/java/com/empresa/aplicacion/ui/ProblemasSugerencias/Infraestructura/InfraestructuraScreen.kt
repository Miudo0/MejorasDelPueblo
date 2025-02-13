package com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.domain.Problema
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.ProblemasSugerencias.MarcarProblemaResueltoViewModel
import com.empresa.aplicacion.ui.ProblemasSugerencias.ValidarProblemaViewModel
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun InfraestructuraScreen(
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
        AppContent(paddingValues = paddingValues)
    }

}

@Composable
private fun AppContent(paddingValues: PaddingValues) {
    val viewModel: InfraestructuraViewModel = hiltViewModel()
    val actualizarViewModel: MarcarProblemaResueltoViewModel = hiltViewModel()

    val deleteViewModel: DeleteProblemasInfraestructuraViewModel = hiltViewModel()

    val state by viewModel.state.collectAsState()

    when (val current = state) {
        is InfraestructuraViewModel.InfraestructuraState.Success -> {
            val problemas = current.problemas

            Log.d("AppContent", "Problemas cargados: ${problemas.size}")

            ProblemasLista(
                problemas = problemas,
                deleteProblema = {
                    Log.d("AppContent", "Eliminar problema: ${it.titulo}")
                    deleteViewModel.deleteProblema(it)
                    Log.d("AppContent", "Problema eliminado: ${it.titulo}")
                },
                paddingValues = paddingValues,
                marcarProblemaSolucionado = {
                    Log.d("AppContent", "Marcar como resuelto problema: ${it.titulo}")
                    actualizarViewModel.marcarComoResuelto(it)
                }

            )
        }

        is InfraestructuraViewModel.InfraestructuraState.Error -> {
            Text(text = current.error)
        }

        is InfraestructuraViewModel.InfraestructuraState.Loading -> CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(paddingValues)
                .size(50.dp)
        )
    }

}


@Composable
fun ProblemasLista(
    problemas: List<Problema>,
    deleteProblema: (Problema) -> Unit,
    paddingValues: PaddingValues,
    marcarProblemaSolucionado: (Problema) -> Unit

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(problemas) { problema ->
            CartaItem(
                problema,
                onDelete = { deleteProblema(problema) },
                marcarProblemaSolucionado = { marcarProblemaSolucionado(problema) }

            )

        }
    }
}

//funcion para crear las cartas
@Composable
private fun CartaItem(
    problema: Problema,
    onDelete: () -> Unit = {},
    marcarProblemaSolucionado: () -> Unit ,
    viewModel: ValidarProblemaViewModel = hiltViewModel()

) {
    val mostrarBotonEliminar = viewModel.mostrarBotonEliminar(problema)
    val mostrarProblemaSolucionado = viewModel.mostrarProblemaSolucionado(problema)
    val confirmarProblemaSolucionado = viewModel.confirmarProblemaSolucionado(problema)




    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (problema.resuelto) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            problema.username?.let {
                Text(
                    text = "Reportado por: $it",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.Start)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)

            )
            problema.titulo?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            MaterialTheme.colorScheme.onPrimaryContainer,
                            RoundedCornerShape(8.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,

                        )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 4.dp, // Grosor de la línea
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f) // Color de la línea
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)

            )
            problema.descripcion?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            if (mostrarBotonEliminar) {

                Button(
                    onClick = {
                        Log.d("CartaItem", "Botón Eliminar presionado")
                        onDelete()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = "Eliminar",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            if (mostrarProblemaSolucionado) {

                Button(
                    onClick = {
                        Log.d("CartaItem", "Botón Problema Solucionado presionado")
                        marcarProblemaSolucionado()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(
                        text = "Problema Solucionado",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )

                }
            }
            if (confirmarProblemaSolucionado) {

                Button(
                    onClick = {
                        Log.d("CartaItem", "Botón Confirmar Solucionado presionado")
                        onDelete()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.padding(top = 16.dp),
                    enabled = true
                ) {
                    Text(
                        text = "Confirmar problema solucionado",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )

                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "A la espera de confirmacion de otro usuario",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


