package com.empresa.aplicacion.ui.problemasSugerencias.componentes


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
import com.empresa.aplicacion.domain.Problema

@OptIn(ExperimentalMaterial3Api::class)
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

    val imagenFondo = painterResource(id = R.drawable.fondo4) // Reemplaza con tu imagen
    val colorFondoContenido = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = if (problema.resuelto) {
//                MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
//            } else {
//                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
//            }
//        )
    ) {


//        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen de fondo de la tarjeta
            Image(
                painter = imagenFondo,
                contentDescription = "Fondo de tarjeta",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorFondoContenido)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val icono = when (problema.tipo) {
                    "Infraestructura" -> painterResource(id = R.drawable.reporteproblemasicono)
                    "Trafico" -> painterResource(id = R.drawable.seguridadfinal)
                    "Medio Ambiente" -> painterResource(id = R.drawable.medioambientefinal)
                    "Seguridad" -> painterResource(id = R.drawable.seguridadfinal)

                    else -> {
                        painterResource(R.drawable.reporteproblemasicono)
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado más pequeño
                ) {
                    Row (
                        Modifier
                            .fillMaxWidth()
//                            .background(
//                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f),
//                                shape = RoundedCornerShape(4.dp)
//                            )
                            .padding(16.dp)
                    ){
                        Image(
                            painter = icono,
                            contentDescription = "Icono de reporte de problemas",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .height(24.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        problema.titulo?.let {

                                Text(
                                    text = "Reporte: $it",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center,

                                )

                        }
                    }
                    problema.descripcion?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }


                Spacer(modifier = Modifier.height(12.dp))

            
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
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
                problema.username?.let {
                    Text(
                        text = "Reportado por: $it",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Right,

                        modifier = Modifier
                            .align(Alignment.End)
                    )
                }
            }
        }


    }
}