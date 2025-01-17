package com.empresa.aplicacion.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.navigation.DestinosMejorasPueblo
import com.empresa.aplicacion.ui.navigation.Home
import com.empresa.aplicacion.ui.navigation.destinosMejoras
import com.empresa.aplicacion.ui.theme.AppTheme

@Composable
fun AplicacionBottomAppBar(

    allScreens: List<DestinosMejorasPueblo>,
    onTabSelected: (DestinosMejorasPueblo) -> Unit,
    currentScreen: DestinosMejorasPueblo

) {

    var seleccion by rememberSaveable { mutableStateOf(0) }


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
                .weight(1f)
                .selectableGroup(),//para que se pueda seleccionar como grupo de una en una
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            allScreens.forEach { screen ->
                botonPrueba(
                    text = screen.route,
                    icono = screen.image,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )

//            botones.forEach { boton ->
//                Boton(
//                    seleccion = seleccion,
//                    navigateTo = { numero->
//                       seleccion = numero
//
//                    when (seleccion) {
//                        0 -> navigateTo(Home.route)
//                        1 -> navigateTo(ProblemasSugerencias.route)
////                        2 -> navigateTo("proyectosScreen")
////                        3 -> navigateTo("notificacionesScreen")
////                        4 -> navigateTo("voluntariadoScreen")
//                    }
//                    },
//                    icono = boton.icono,
//                    nombre = boton.nombre,
//                    numero = boton.numero
//                )
//
//            }
            }

        }
    }

//    @Composable
//    fun Boton(
//        seleccion: Int,
//        navigateTo: (Int) -> Unit,
//        icono: Int,
//        nombre: String,
//        numero: Int,
//    ) {
//        Column(
//            modifier = Modifier,
//
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            IconButton(
//                onClick = { navigateTo(numero) }
//            ) {
//                Image(
//                    painterResource(id = icono),
//                    contentDescription = nombre,
//                    modifier = Modifier
//                        .size(if (seleccion == numero) 48.dp else 24.dp),
//                    colorFilter = ColorFilter.tint(
//                        if (seleccion == numero) {
//                            MaterialTheme.colorScheme.primaryContainer
//
//                        } else MaterialTheme.colorScheme.surfaceVariant,
//
//                        )
//                )
//            }
//            Text(
//                text = if (seleccion == numero) nombre else "",
//                style = MaterialTheme.typography.labelSmall,
//            )
//        }
//    }
}

@Composable
fun botonPrueba(
    text: String,
    icono: Int,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val color = MaterialTheme.colorScheme.onSurface
    val durationMillis =
        if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Image(
            painterResource(id = icono),
            contentDescription = text,

            colorFilter = ColorFilter.tint(
                if (selected) {
                    MaterialTheme.colorScheme.primaryContainer

                } else MaterialTheme.colorScheme.surfaceVariant,

                )
        )

    }

}


data class InformacionBoton(
    val icono: Int,
    val nombre: String,
    val numero: Int
)

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100

@Preview
@Composable
fun AplicacionBottomAppBarPreview() {
    AppTheme {
        AplicacionBottomAppBar(

            allScreens = destinosMejoras,
            onTabSelected = {},
            currentScreen = Home
        )
    }

}