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



    val imagenFondo = painterResource(id = R.drawable.fondo4)
    val imagenFondo2 = painterResource(id = R.drawable.fondo5)
    val colorFondoContenido = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),


    ) {


//        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen de fondo de la tarjeta
            if (problema.resuelto) {
                Image(
                    painter = imagenFondo2,
                    contentDescription = "Fondo de tarjeta",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            } else {
                Image(
                    painter = imagenFondo,
                    contentDescription = "Fondo de tarjeta",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorFondoContenido)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val icono = when (problema.tipo) {
                    "Infraestructura" -> painterResource(id = R.drawable.handyman_24dp_e8eaed_fill0_wght400_grad0_opsz24)
                    "Trafico" -> painterResource(id = R.drawable.traffic_24dp_e8eaed_fill0_wght400_grad0_opsz24)
                    "Medio Ambiente" -> painterResource(id = R.drawable.nature_24dp_e8eaed_fill0_wght400_grad0_opsz24)
                    "Seguridad" -> painterResource(id = R.drawable.health_and_safety_24dp_e8eaed_fill0_wght400_grad0_opsz24)

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
                                    text = "Reporte: ${it.replaceFirstChar { char -> char.uppercase() }}",
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
                            text = it.replaceFirstChar { char -> char.uppercase() },
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