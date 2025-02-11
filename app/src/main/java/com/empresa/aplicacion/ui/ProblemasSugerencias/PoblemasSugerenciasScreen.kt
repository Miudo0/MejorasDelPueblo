package com.empresa.aplicacion.ui.ProblemasSugerencias

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.navigation.DestinosMejorasPueblo
import com.empresa.aplicacion.ui.navigation.Infraestructura
import com.empresa.aplicacion.ui.navigation.MedioAmbiente
import com.empresa.aplicacion.ui.navigation.ProblemasSugerencias
import com.empresa.aplicacion.ui.navigation.Seguridad
import com.empresa.aplicacion.ui.navigation.Trafico
import com.empresa.aplicacion.ui.navigation.destinosMejoras


//creo la clase de cartas de la seccion Problemas
data class CartaProblemas(
    @DrawableRes val imagen: Int,
    @StringRes val titulo: Int,
    val route : String

)

//creo la lista de las cartas
var listaCartasProblemas: List<CartaProblemas> = listOf(

    CartaProblemas(
        R.drawable.infraestuctura,
        R.string.infraestructura,
        route = Infraestructura.route
    ),
    CartaProblemas(
        R.drawable.trafico,
        R.string.trafico,
        route = Trafico.route
    ),
    CartaProblemas(
        R.drawable.seguridad,
        R.string.seguridad,
        route = Seguridad.route
    ),
    CartaProblemas(
        R.drawable.medioambiente,
        R.string.medio_ambiente,
        route = MedioAmbiente.route
    )
)

@Composable
fun ProblemasScreen(
    onTabSelected: (DestinosMejorasPueblo) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToReportarProblema: () -> Unit,
    navigateToOpcion: (String) -> Unit


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
                    onTabSelected(ruta)
                },
                currentScreen = ProblemasSugerencias
            )
        }
    ) { paddingValues ->
        ProblemasApp(
            paddingValues = paddingValues,
            navigateToReportarProblema,
            navigateToOpcion
        )
    }
}

@Composable
fun ProblemasApp(
    paddingValues: PaddingValues,
    navigateToReportarProblema: () -> Unit,
    navigateToOpcion: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()

            .padding(paddingValues),//aplicar a la columna
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //para que recorra la lista en cuadrantes
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Dos columnas para el cuadrante
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(listaCartasProblemas) { carta ->
                CartaItem(
                    carta = carta,
                    navigateToOpcion = navigateToOpcion
                )
            }
        }
        Button(
            onClick = { navigateToReportarProblema() },
        ) {
            Text(text = "Reportar un problema")
        }
    }
}


//funcion para crear las cartas
@Composable
private fun CartaItem(
    carta: CartaProblemas,
    navigateToOpcion: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navigateToOpcion(carta.route)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.primary
        )
    ) {
        FotoCarta(carta.imagen)
        TextoCarta(carta.titulo)
    }


}

//funcion para las fotos de las cartas
@Composable
private fun FotoCarta(
    @DrawableRes foto: Int,
    modifier: Modifier = Modifier
) {
    Image(
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(100.dp),
        painter = painterResource(foto),
        contentDescription = null
    )
}

//funcion para el texto de las cartas
@Composable
private fun TextoCarta(
    @StringRes titulo: Int
) {
    Text(
        text = stringResource(titulo),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}


//@Preview
//@Composable
//fun AppPreview() {
//    AppTheme {
//        ProblemasScreen(
//            viewModel = viewModel(),
//            onTabSelected = {}
//
//        )
//    }
//
//}