package com.empresa.aplicacion.ui.ProblemasSugerencias

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.domain.Problema

@Composable
 fun CartaItem(
    problema: Problema,
    onDelete: () -> Unit = {},
    marcarProblemaSolucionado: () -> Unit,
    viewModel: ValidarProblemaViewModel = hiltViewModel()
) {
    val mostrarBotonEliminar = viewModel.mostrarBotonEliminar(problema)
    val mostrarProblemaSolucionado = viewModel.mostrarProblemaSolucionado(problema)
    val confirmarProblemaSolucionado = viewModel.confirmarProblemaSolucionado(problema)

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
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
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
                    problema.username?.let {
                        Text(
                            text = "Reportado por: $it",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }

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
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Box para los botones
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (mostrarBotonEliminar) {
                        BotonAccion(
                            texto = "Eliminar",
                            onClick = {
                                Log.d("CartaItem", "Botón Eliminar presionado")
                                onDelete()
                            },
                        )
                    }

                    if (mostrarProblemaSolucionado) {
                        BotonAccion(
                            texto = "Problema Solucionado",
                            onClick = {
                                Log.d("CartaItem", "Botón Problema Solucionado presionado")
                                marcarProblemaSolucionado()
                            },
                        )
                    }

                    if (confirmarProblemaSolucionado) {
                        BotonAccion(
                        texto = "Confirmar problema solucionado",


                            onClick = {
                                Log.d("CartaItem", "Botón Confirmar Solucionado presionado")
                                onDelete()
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "A la espera de confirmación de otro usuario",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        if (!mostrarProblemaSolucionado && !mostrarBotonEliminar) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "A la espera de confirmación de otro usuario para confirmar el problema solucionado",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}