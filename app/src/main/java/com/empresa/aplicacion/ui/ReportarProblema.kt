package com.empresa.aplicacion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias

enum class TipoProblema(val descripcion: String) {
    INFRAESTRUCTURA("Infraestructura"),
    TRAFICO("Tráfico"),
    SEGURIDAD("Seguridad"),
    MEDIO_AMBIENTE("Medio Ambiente")
}


@Composable
fun ReportarScreen(
    viewModel: ReportarProblemaViewModel = hiltViewModel(),
    navigateTo: (String) -> Unit
) {
    var descripcion by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf(TipoProblema.INFRAESTRUCTURA) }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "Reportes o Sugerencias",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = {
                Text(
                    text = "Titulo"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = {
                Text(
                    text = "Describe el problema o sugerencia"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Selecciona el tipo de problema:", style = MaterialTheme.typography.titleMedium)

        Column {
            TipoProblema.entries.forEach { opcion ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = tipo == opcion,
                        onClick = { tipo = opcion }
                    )
                    Text(text = opcion.descripcion, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
           viewModel.nuevoProblema(titulo, descripcion,  tipo.descripcion)



            }
        ) {
            Text(
                text = "Enviar"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
    when(state){
        is ReportarProblemaViewModel.NewProblemState.Success ->{navigateTo(ProblemasSugerencias.route)}
        is ReportarProblemaViewModel.NewProblemState.Error ->{}
        is ReportarProblemaViewModel.NewProblemState.Loading ->{}

        }
}

//
//@Preview
//@Composable
//fun ReportarScreenPreview() {
//    ReportarScreen()
//}