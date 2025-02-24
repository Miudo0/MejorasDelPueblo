package com.empresa.aplicacion.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.ui.navigation.DestinosMejorasPueblo

@Composable
fun AplicacionBottomAppBar(

    allScreens: List<DestinosMejorasPueblo>,
    onTabSelected: (DestinosMejorasPueblo) -> Unit,
    currentScreen: DestinosMejorasPueblo,
    badgeScreens: Set<String> = emptySet()

) {

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            allScreens.forEach { screen ->
                BotonBarItem(
                    text = screen.route,
                    icono = screen.image,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen,
                    showBadge = screen.route in badgeScreens,
                    modifier = Modifier
                        .weight(1f)

                )

            }

        }
    }
}

@Composable
fun BotonBarItem(
    text: String,
    icono: Int,
    onSelected: () -> Unit,
    selected: Boolean,
    showBadge: Boolean = false,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.onTertiary
    val animatedColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onSecondaryContainer else color.copy(alpha = 0.8f),
        animationSpec = tween(durationMillis = 200)
    )
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 1f,
        animationSpec = tween(durationMillis = 100)
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable(onClick = onSelected)
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BadgedBox(
            badge = {
                if (showBadge) {
                    Badge() // Muestra solo el punto
                }
            }
        ){
        Icon(
            painter = painterResource(id = icono),
            contentDescription = text,
            tint = animatedColor,
            modifier = Modifier
                .size(24.dp)
        )
        }
        if(selected) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = animatedColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


