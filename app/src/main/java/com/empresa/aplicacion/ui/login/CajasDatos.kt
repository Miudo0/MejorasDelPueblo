package com.empresa.aplicacion.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

data class CajasDatos(

    val value: String,
    val onValueChange: (String) -> Unit,
    val label: String,
    val placeholder: String,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val isError: Boolean = false
)
@Composable
 fun ErrorMensaje(errorMessage: String) {
    if (errorMessage.isNotEmpty()) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Composable
 fun CajaTexto(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    errorMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    OutlinedTextField(
        value = value,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        isError = errorMessage.isNotEmpty(),
        visualTransformation = visualTransformation,
    )
}