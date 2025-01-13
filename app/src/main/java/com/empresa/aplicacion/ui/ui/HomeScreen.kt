package com.empresa.aplicacion.ui.ui

import android.content.res.Configuration
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.ColumnaCartas
import com.empresa.aplicacion.ui.listaElementosAccesibles
import com.empresa.aplicacion.ui.ui.theme.AppTheme


//base de la app con el Scaffold

@Composable
fun HomeScreen(navigateTo: (Int) -> Unit) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar()
        },
        bottomBar = {
            AplicacionBottomAppBar(navigateTo = navigateTo)
        }
    ) { paddingValues ->
        App(
            navigateTo = navigateTo,
            paddingValues = paddingValues)
    }
}



@Composable
fun App(
    navigateTo: (Int) -> Unit,
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
                navigateTo = navigateTo
            )
        }
        item {
            ColumnaCartas()
        }
    }
}



@Composable
private fun FilaElementosAccesibles(
    navigateTo: (Int) -> Unit
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
                            R.drawable.reporteproblemas -> navigateTo(1) // Enviar identificador de pantalla
                            R.drawable.notificaciones -> navigateTo(2)
                            R.drawable.voluntariado -> navigateTo(3)
                            else -> navigateTo(0) // Pantalla por defecto
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


//barra inferior




//vista previa
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Previsualizacion() {
    AppTheme {
        HomeScreen {

        }

    }
}

//vista previa
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun DarkPrevisualizacion() {
    AppTheme {
        HomeScreen {

        }

    }
}