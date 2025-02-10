package com.empresa.aplicacion.ui.ProblemasSugerencias.MedioAmbiente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun MedioAmbienteScreen(
    navigateTo: (String) -> Unit,



    ) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar(navigateToLogin = navigateTo,
                navigateToHome = navigateTo)
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
    val state = viewModel.state.collectAsState()


    //cambia a by
    when (val current = state.value) {
        is MedioAmbienteState.Success -> {
            val problemas = current.problemas
            ProblemasLista(problemas, paddingValues)
        }

        is MedioAmbienteState.Error -> {
            Text(text = current.error)
        }

        is MedioAmbienteState.Loading -> {
            LinearProgressIndicator()
        }
    }

}

@Composable
fun ProblemasLista(problemas: List<Problemas>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(problemas) { problema ->
            CartaItem(problema)
        }
    }
}

//funcion para crear las cartas
@Composable
private fun CartaItem(problema: Problemas) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Row() {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                problema.titulo?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                problema.descripcion?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }

            }
            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    modifier = Modifier
                        .size(24.dp)

                )
            }

        }
    }
}