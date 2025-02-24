package com.empresa.aplicacion.ui.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.navigation.Home
import com.empresa.aplicacion.ui.navigation.destinosMejoras


//base de la app con el Scaffold

@Composable
fun HomeScreen(
    navigateToProblemas: () -> Unit,
    navigateToRegistro: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateTo: (String) -> Unit,

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
                currentScreen = Home
            )
        }
    ) { paddingValues ->

        App(
            navigateToProblemas,
            navigateToRegistro,
            paddingValues = paddingValues
        )
    }
}


@Composable
fun App(
    navigateToProblemas: () -> Unit,
    navigateToRegistro: () -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        Modifier
            .padding(paddingValues)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            FilaElementosAccesibles(
                navigateToProblemas,
                navigateToRegistro,
            )
        }
        item {
            ColumnaCartas()
        }
    }
}

@Composable
private fun FilaElementosAccesibles(
    navigateToProblemas: () -> Unit,
    navigateToRegistro: () -> Unit,
) {
    Log.d("LISTA_ELEMENTOS", "Número de elementos: ${listaElementosAccesibles.size}")

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp), // Espacio entre los elementos
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        listaElementosAccesibles.forEach { item ->
            Box(
                modifier = Modifier
                    .weight(1f)

            ) {
                ElementosAccesibles(
                    drawable = item.first,
                    text = item.second,
                    onClick = {
                        when (item.first) {
                            R.drawable.reporteproblemasfinal -> navigateToProblemas()
                            R.drawable.turegistro -> navigateToRegistro()
                        }
                        Log.d("CLICKABLE", "item.first: ${item.first}")
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



@Composable
private fun ElementosAccesibles(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f, label = "")

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier

            .scale(scale)
            .clickable(
                //hace un efecto al tocar
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
                onClick = {
                    isPressed = true
                    onClick()
                    isPressed = false
                }
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)

            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = stringResource(text),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
