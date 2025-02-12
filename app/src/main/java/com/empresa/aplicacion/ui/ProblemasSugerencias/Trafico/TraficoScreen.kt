package com.empresa.aplicacion.ui.ProblemasSugerencias.Trafico

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.data.room.ProblemasDatabase.Problemas
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
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
    val state = viewModel.state.collectAsState()

    when (val current = state.value) {
        is TraficoState.Success -> {
            val problemas = current.problemas
            ProblemasLista(
                problemas,
                paddingValues,
                deleteProblema = { deleteViewModel.deleteProblemaTrafico(it) })
        }

        is TraficoState.Error -> {
            Text(text = current.error)
        }

        is TraficoState.Loading -> LinearProgressIndicator()
    }


}

@Composable
fun ProblemasLista(
    problemas: List<Problemas>,
    paddingValues: PaddingValues,
    deleteProblema: (Problemas) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(problemas) { problema ->
            CartaItem(
                problema,
                onDelete = { deleteProblema(problema) })
        }
    }
}

@Composable
private fun CartaItem(
    problema: Problemas,
    onDelete: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

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

            Button(
                onClick = { onDelete() },
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
    }
}
