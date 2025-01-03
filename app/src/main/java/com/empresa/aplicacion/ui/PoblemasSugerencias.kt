package com.empresa.aplicacion.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R


//creo la clase de cartas de la seccion Problemas
data class CartaProblemas(
    @DrawableRes val imagen: Int,
    @StringRes val titulo: Int

)

//creo la lista de las cartas
var listaCartasProblemas: List<CartaProblemas> = listOf(

    CartaProblemas(
        R.drawable.infraestuctura,
        R.string.infraestructura
    ),
    CartaProblemas(
        R.drawable.trafico,
        R.string.trafico
    ),
    CartaProblemas(
        R.drawable.seguridad,
        R.string.seguridad
    ),
    CartaProblemas(
        R.drawable.medioambiente,
        R.string.medio_ambiente
    )
)

@Composable
fun AppProblemas(

) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onBackground),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //para que recorra la lista en cuadrantes
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Dos columnas para el cuadrante
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(listaCartasProblemas) { carta ->
                CartaItem(carta = carta)
            }
        }
    }
}


//funcion para crear las cartas
@Composable
private fun CartaItem(
    carta: CartaProblemas,
) {
    Card(
        modifier = Modifier
            .padding(10.dp),
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


@Preview
@Composable
fun AppPreview() {
    AppProblemas()
}