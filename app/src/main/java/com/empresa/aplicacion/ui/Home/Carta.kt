package com.empresa.aplicacion.ui.Home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R

data class Carta(
    @DrawableRes val imagen: Int,
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int,
    var expanded: Boolean = false

)
//creo la lista de las cartas.
var listaCartas: List<Carta> = listOf(
    Carta(
        R.drawable.reporteproblemas,
        R.string.reporte_de_problemas_y_sugerencias,
        R.string.informa
    ),
    Carta(
        R.drawable.proyectoscomunitarios,
        R.string.proyectos_comunitarios,
        R.string.texto_proyectos

    ),
    Carta(
        R.drawable.notificaciones,
        R.string.notificaciones_e_informaci_n_relevante,
        R.string.texto_notificaciones
    ),
    Carta(
        R.drawable.voluntariado,
        R.string.voluntariado_y_ayudas,
        R.string.texto_voluntariado
    )
)


 val listaElementosAccesibles: List<Pair<Int, Int>>
    get() = listOf(
        R.drawable.reporteproblemas to R.string.reportar,
        R.drawable.notificaciones to R.string.notificaciones,
        R.drawable.voluntariado to R.string.voluntariado,
        R.drawable.proyectoscomunitarios to R.string.proyectos

    )


@Composable
fun ColumnaCartas() {
    Column(

        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)

    ) {
        //no puedo poner lazyColumn dentro de otra lazyColumn despues
        listaCartas.forEach { carta ->
            CartaItem(
                carta = carta,

                )
        }
    }
}


@Composable
private fun CartaItem(
    carta: Carta,
    expanded: Boolean = false,
) {
    var isExpanded by rememberSaveable { mutableStateOf(expanded) }
    val color by animateColorAsState(
        targetValue = if (isExpanded) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.primary, label = "Animacion de color"
    )
    Card(
        modifier = Modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        FotoCarta(carta.imagen)
        InformacionCarta(
            carta.titulo, carta.descripcion,
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
        )
    }
}


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


@Composable
private fun InformacionCarta(
    @StringRes titulo: Int,
    @StringRes descripcion: Int,
    expanded: Boolean,
    onExpandedChange: () -> Unit,
) {
    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .padding(16.dp)
    ) {
        //fila con el titulo y el boton expandir
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(titulo),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            BotonExpandir(
                expanded = expanded,
                onClick = onExpandedChange
            )
        }
        if (expanded) {
            Text(
                text = stringResource(descripcion),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
private fun BotonExpandir(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) {
                Icons.Filled.KeyboardArrowUp
            } else {
                Icons.Filled.KeyboardArrowDown
            },
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer

        )
    }
}
