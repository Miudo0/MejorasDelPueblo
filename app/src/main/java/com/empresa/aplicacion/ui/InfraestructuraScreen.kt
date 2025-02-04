package com.empresa.aplicacion.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras
import com.empresa.aplicacion.ui.theme.AppTheme

@Composable
fun InfraestructuraScreen(
    navigateTo: (String) -> Unit,
    viewModel: ValidarUSuarioViewModel,

    ) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar(viewModel = viewModel)
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
    val viewModel: InfraestructuraViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    when (val current = state.value) {
        is InfraestructuraState.Success -> {
            val problemas = current.problemas
            ProblemasLista(problemas)
        }

        is InfraestructuraState.Error -> {
            Text(text = current.error)
        }
        is InfraestructuraState.Loading -> LinearProgressIndicator()
    }


}

@Composable
fun ProblemasLista(problemas: List<Problemas>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
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
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


    @Composable
    @Preview
    fun InfraestructuraPreview() {
        AppTheme {
            InfraestructuraScreen(
                navigateTo = {},
                viewModel = hiltViewModel(),

            )

        }
    }