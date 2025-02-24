package com.empresa.aplicacion.ui.problemasSugerencias.reportar

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.destinosMejoras
import kotlinx.coroutines.delay

enum class TipoProblema(val descripcion: String) {
    INFRAESTRUCTURA("Infraestructura"),
    TRAFICO("Trafico"),
    SEGURIDAD("Seguridad"),
    MEDIO_AMBIENTE("Medio Ambiente")
}

@Composable
fun ReportarScreen(
    navigateToProblemasSugerencias: () -> Unit,
    navigateTo: (String) -> Unit
) {
    Scaffold(

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
        ReportarContent(
            navigateToProblemasSugerencias = navigateToProblemasSugerencias,
            paddingValues = paddingValues
        )
    }

}

@Composable
fun ReportarContent(
    paddingValues: PaddingValues,
    viewModel: ReportarProblemaViewModel = hiltViewModel(),
    navigateToProblemasSugerencias: () -> Unit
) {
    var descripcion by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf(TipoProblema.INFRAESTRUCTURA) }

    val state by viewModel.state.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reportes o Sugerencias",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = {
                            Text(
                                text = "Título",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        textStyle = TextStyle(fontSize = 15.sp), // Aumenta el tamaño del texto dentro del campo
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = {
                            Text(
                                text = "Describe el problema o sugerencia",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Selecciona el tipo de problema:",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    )

                    TipoProblema.entries.forEach { opcion ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            RadioButton(
                                selected = tipo == opcion,
                                onClick = { tipo = opcion }
                            )
                            Text(
                                text = opcion.descripcion,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.nuevoProblema(titulo, descripcion, tipo.descripcion)

                            Log.d("ReportarScreen", "Botón presionado")

                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondaryContainer),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)

                    ) {
                        Text(
                            text = "Reportar",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is ReportarProblemaViewModel.NewProblemState.Success -> showDialog = true
            is ReportarProblemaViewModel.NewProblemState.Error -> {
                Text("Error al enviar el reporte", color = MaterialTheme.colorScheme.error)
            }

            is ReportarProblemaViewModel.NewProblemState.Loading -> {

            }
        }
        // AlertDialog para mostrar que el reporte fue enviado
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        "Reporte Enviado",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                text = {
                    Text(
                        "Tu problema o sugerencia ha sido reportado con éxito.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                containerColor = MaterialTheme.colorScheme.surfaceVariant, // Color del fondo
                textContentColor = MaterialTheme.colorScheme.onSurface, // Color del texto
                shape = RoundedCornerShape(16.dp), // Bordes redondeados
                confirmButton = {} // No hay botón de confirmación
            )
            // Ocultar automáticamente después de 2 segundos
            LaunchedEffect(Unit) {
                delay(1500)
                showDialog = false
                navigateToProblemasSugerencias()
            }
        }
    }
}
