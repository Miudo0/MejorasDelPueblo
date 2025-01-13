package com.empresa.aplicacion.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.ui.theme.AppTheme

@Composable
fun AplicacionBottomAppBar(navigateTo: (Int) -> Unit) {

    var seleccion by remember { mutableStateOf(0) }

    val botones = listOf(
        InformacionBoton(R.drawable.homeicon, "Inicio", 0),
        InformacionBoton(R.drawable.reporteproblemasicono, "Reporte", 1),
        InformacionBoton(R.drawable.proyectoscomunitariosicono, "Proyectos", 2),
        InformacionBoton(R.drawable.notificacionesicono, "Notificaciones", 3),
        InformacionBoton(R.drawable.voluntariadoayudas, "Voluntariado", 4)
    )



    BottomAppBar(
        modifier = Modifier,
        MaterialTheme.colorScheme.secondary,


        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            botones.forEach { boton ->
                Boton(
                    seleccion = seleccion,
                    navigateTo = { numero ->
                        navigateTo(numero)
                    },
                    icono = boton.icono,
                    nombre = boton.nombre,
                    numero = boton.numero
                )

            }
        }

//
//        Boton(
//            seleccion = seleccion,
//            onClick = { seleccion =  0 },
//            icono = R.drawable.homeicon,
//            nombre = "Inicio",
//            numero = 0
//        )
//
//        Boton(
//            seleccion = seleccion,
//            onClick = { seleccion =  1 },
//            icono = R.drawable.reporteproblemasicono,
//            nombre = "Reporte",
//            numero = 1
//
//        )
//        Boton(
//            seleccion = seleccion,
//            onClick = { seleccion =  2 },
//            icono = R.drawable.proyectoscomunitariosicono,
//            nombre = "Proyectos ",
//            numero = 2
//        )
//        Boton(
//            seleccion = seleccion,
//            onClick = { seleccion =  3 },
//            icono = R.drawable.notificacionesicono,
//            nombre = "Notificaciones",
//            numero = 3
//
//        )


//        Column(
//            modifier = Modifier.weight(1f),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            IconButton(onClick = { seleccion = 0 }) {
//                Image(
//                    painter = painterResource(R.drawable.homeicon),
//                    contentDescription = "Inicio",
//                    modifier = Modifier
//                        .size(if (seleccion == 0) 48.dp else 24.dp),
//                   colorFilter = ColorFilter.tint(
//                    if (seleccion == 0) {
//                        MaterialTheme.colorScheme.primaryContainer
//
//                    } else MaterialTheme.colorScheme.surfaceVariant,
//
//                    )
//                )
//            }
//            Text(
//                text = if (seleccion == 0) "Inicio" else "",
//                style = MaterialTheme.typography.labelSmall,
//            )
//        }

//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(onClick = { seleccion = 1 }) {
//            Image(
//                painter = painterResource(R.drawable.reporteproblemasicono),
//                contentDescription = "ReporteProblemas",
//                modifier = Modifier
//                    .size(if (seleccion == 1) 48.dp else 24.dp),
//                colorFilter = ColorFilter.tint(
//                    if (seleccion == 1) MaterialTheme.colorScheme.primaryContainer
//                    else MaterialTheme.colorScheme.surfaceVariant
//
//                )
//            )
//        }
//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(onClick = { seleccion = 2 }) {
//            Image(
//                painter = painterResource(R.drawable.proyectoscomunitariosicono),
//                contentDescription = "Notificaciones",
//                modifier = Modifier
//                    .size(if (seleccion == 2) 48.dp else 24.dp),
//                colorFilter = ColorFilter.tint(
//                    if (seleccion == 2) MaterialTheme.colorScheme.primaryContainer
//                    else MaterialTheme.colorScheme.surfaceVariant
//
//                )
//            )
//        }
//        Spacer(modifier = Modifier.weight(1f))
//        IconButton(onClick = { seleccion = 3 }) {
//            Image(
//                painter = painterResource(R.drawable.notificacionesicono),
//                contentDescription = "ProyectosComunitarios",
//                modifier = Modifier
//                    .size(if (seleccion == 3) 48.dp else 24.dp),
//                colorFilter = ColorFilter.tint(
//                    if (seleccion == 3) MaterialTheme.colorScheme.primaryContainer
//                    else MaterialTheme.colorScheme.surfaceVariant
//
//                )
//            )
//
//        }
//        Spacer(modifier = Modifier.weight(1f))
//        IconButton(onClick = { seleccion = 4 }) {
//            Image(
//                painter = painterResource(R.drawable.voluntariadoayudas),
//                contentDescription = "Voluntariado",
//                modifier = Modifier
//                    .size(if (seleccion == 4) 48.dp else 24.dp),
//                colorFilter = ColorFilter.tint(
//                    if (seleccion == 4) MaterialTheme.colorScheme.primaryContainer
//                    else MaterialTheme.colorScheme.surfaceVariant
//
//                )
//
//            )
//
//        }


    }
}

@Composable
fun Boton(
    seleccion: Int,
    navigateTo: (Int) -> Unit,
    icono: Int,
    nombre: String,
    numero: Int,
) {
    Column(
        modifier = Modifier,

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = { navigateTo(numero) }
        ) {
            Image(
                painterResource(id = icono),
                contentDescription = nombre,
                modifier = Modifier
                    .size(if (seleccion == numero) 48.dp else 24.dp),
                colorFilter = ColorFilter.tint(
                    if (seleccion == numero) {
                        MaterialTheme.colorScheme.primaryContainer

                    } else MaterialTheme.colorScheme.surfaceVariant,

                    )
            )
        }
        Text(
            text = if (seleccion == numero) nombre else "",
            style = MaterialTheme.typography.labelSmall,
        )
    }
}


data class InformacionBoton(
    val icono: Int,
    val nombre: String,
    val numero: Int
)


@Preview
@Composable
fun AplicacionBottomAppBarPreview() {
    AppTheme {
        AplicacionBottomAppBar(navigateTo = {})
    }

}