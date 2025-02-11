package com.empresa.aplicacion.ui.Home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    navigateToNotificaicones: () -> Unit,
    navigateToVoluntariado: () -> Unit,
    navigateToProyectos: () -> Unit,
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
            navigateToNotificaicones,
            navigateToVoluntariado,
            navigateToProyectos,
            paddingValues = paddingValues
        )
    }
}


@Composable
fun App(
    navigateToProblemas: () -> Unit,
    navigateToNotificaicones: () -> Unit,
    navigateToVoluntariado: () -> Unit,
    navigateToProyectos: () -> Unit,
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
                navigateToNotificaicones,
                navigateToVoluntariado,
                navigateToProyectos
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
    navigateToNotificaicones: () -> Unit,
    navigateToVoluntariado: () -> Unit,
    navigateToProyectos: () -> Unit,

    ) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier


    )
    {
        items(listaElementosAccesibles) { item ->
            ElementosAccesibles(
                drawable = item.first,
                text = item.second,
                modifier = Modifier
                    .clickable {
                        when (item.first) {
                            R.drawable.reporteproblemas -> navigateToProblemas() // Enviar identificador de pantalla
                            R.drawable.notificaciones -> navigateToNotificaicones()
                            R.drawable.voluntariado -> navigateToVoluntariado()
                            R.drawable.proyectoscomunitarios -> navigateToProyectos()
                            //   else -> navigateTo(Login.route) // Pantalla por defecto
                        }
                        Log.d("CLICKABLE", "item.first: ${item.first}")
                    }
            )
        }
    }
}

@Composable
private fun ElementosAccesibles(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )
        Text(
            text = stringResource(text),
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
