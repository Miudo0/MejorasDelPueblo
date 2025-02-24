package com.empresa.aplicacion.ui.registroTareasPendientes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
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

            Image(
                painter = painterResource(id = R.drawable.boladesierto),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .graphicsLayer(alpha = 0.8f) // Opacidad para mejorar legibilidad
            )

            Text(
                text = "No hay problemas registrados. Comienza a colaborar!!!",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp // Espaciado entre letras
                ),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-40).dp) // Desplazamiento del texto hacia arriba
            )
        }
    } else {

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
            ) {
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
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
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
                                    text = "Reporte: ${it.replaceFirstChar { char -> char.uppercase()}}",
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
}