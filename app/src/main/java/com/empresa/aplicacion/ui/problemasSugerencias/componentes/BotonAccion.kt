package com.empresa.aplicacion.ui.problemasSugerencias.componentes

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BotonAccion(
    texto: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    // Animación de escala: se reduce el tamaño del botón al presionar
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f, // Reducción de escala al presionar
        animationSpec = tween(durationMillis = 150) // Duración de la animación
    )

    val opacity by animateFloatAsState(
        targetValue = if (isPressed) 0.7f else 1f, // Reducción de opacidad al presionar
        animationSpec = tween(durationMillis = 150) // Duración de la animación
    )
    Button(
        onClick = {
            onClick()
            isPressed = true
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .graphicsLayer(
                scaleX = scale, scaleY = scale, // Escalar el botón
                alpha = opacity // Cambiar la opacidad
            ),

        enabled = enabled
    ) {
        Text(
text = texto,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(100) // Espera para hacer que la animación vuelva
            isPressed = false
        }
    }
}