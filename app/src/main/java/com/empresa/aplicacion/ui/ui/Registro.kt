package com.empresa.aplicacion.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistroScreen(navigatetoHome: () -> Unit) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        Text(
            text = "Registro de Usuario",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary

        )
        Spacer(modifier = Modifier.height(16.dp))
        CajaTexto(
            value = username,
            { username = it },
            "UserName",
            "Escriba su nombre de usuario",
            errorMessage = errorMessage
        )
        Spacer(modifier = Modifier.height(16.dp))
        CajaTexto(
            value = password,
            { password = it },
            "Contraseña",
            "Escriba su contraseña",
            errorMessage = errorMessage,
            visualTransformation = PasswordVisualTransformation(),


            )
        Spacer(modifier = Modifier.height(16.dp))
        CajaTexto(
            value = email,
            { email = it },
            "Email",
            "Escriba su email",
            errorMessage = errorMessage
        )
        Spacer(modifier = Modifier.height(24.dp))

        ErrorMensaje(errorMessage)
        Button(
            onClick = {
                if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                    errorMessage = ""
                    navigatetoHome()
                } else {
                    errorMessage = "Por favor, complete todos los campos"
                }
            },
            modifier = Modifier
                .height(60.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.bodyMedium
            )
        }

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

@Preview
@Composable
fun RegistroPreview() {
    RegistroScreen(
        navigatetoHome = {}
    )
}