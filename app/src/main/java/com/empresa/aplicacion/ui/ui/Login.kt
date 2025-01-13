package com.empresa.aplicacion.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.ui.theme.AppTheme

@Composable
fun LoginScreen(navigateTo: (Int) -> Unit) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar()
        },
//        bottomBar = {
//            AplicacionBottomAppBar()
//        }
    ) { paddingValues ->
        LoginApp(
            navigateTo = navigateTo,
            paddingValues = paddingValues)
    }
}




@Composable
fun LoginApp(
    navigateTo: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    var usuario by rememberSaveable { mutableStateOf("") }
    var contraseña by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val datosLogin = listOf(

        CajasDatos(
            value = usuario,
            onValueChange = { usuario = it },
            label = stringResource(R.string.usuario_login),
            placeholder = stringResource(R.string.placeholder_login_usuario)
        ),
        CajasDatos(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = stringResource(R.string.pass_login),
            placeholder = stringResource(R.string.placeholder_login_pass),
            visualTransformation = PasswordVisualTransformation()
        )
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary

        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        datosLogin.forEach { dato ->
            CajaTexto(
                value = dato.value,
                onValueChange = dato.onValueChange,
                label = dato.label,
                placeholder = dato.placeholder,
                errorMessage = errorMessage
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        ErrorMensaje(errorMessage = errorMessage)

//        OutlinedTextField(
//            value = usuario,
//            onValueChange = { usuario = it },
//            label = {
//                Text(
//                    text = "Usuario",
//                    style = MaterialTheme.typography.bodyMedium,
//                )
//            },
//            placeholder = { Text(text = "Ingrese su usuario") },
//            isError = errorMessage.isNotEmpty(), // Mostrar error si hay mensaje
//
//
//        )
//
//        Spacer(
//            modifier = Modifier
//                .height(16.dp)
//        )
//        OutlinedTextField(
//            value = contraseña,
//            onValueChange = { contraseña = it },
//            label = {
//                Text(
//                    text = "Contraseña",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            },
//            placeholder = { Text(text = "Ingrese su contraseña") },
//            isError = errorMessage.isNotEmpty(),
//            visualTransformation = PasswordVisualTransformation(), // Ocultar texto de la contraseña
//
//        )
//        if (errorMessage.isNotEmpty()) {
//            Text(
//                text = errorMessage,
//                color = MaterialTheme.colorScheme.error,
//                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier.padding(top = 8.dp)
//            )
//        }
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Button(

            onClick = {
                if (usuario.isNotEmpty() && contraseña.isNotEmpty()) {
                    errorMessage = "" // Limpiar mensaje de error
                    navigateTo(1)
                } else {
                    errorMessage = "Por favor, ingresa usuario y contraseña"
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
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = "No tienes una cuenta?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .clickable {
                    navigateTo(2)
                }
        )
    }
}


//@Composable
//private fun CajaTexto(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    placeholder: String,
//    errorMessage: String = "",
//    visualTransformation: VisualTransformation = VisualTransformation.None
//) {
//
//    OutlinedTextField(
//        value = value,
//        label = {
//            Text(
//                text = label,
//                style = MaterialTheme.typography.bodyMedium
//            )
//        },
//        onValueChange = onValueChange,
//        placeholder = { Text(text = placeholder) },
//        isError = errorMessage.isNotEmpty(),
//        visualTransformation = visualTransformation,
//    )
//}




@Preview(showBackground = true)
@Composable
fun AppLoginPreview() {
    AppTheme {
        LoginScreen(navigateTo = {})
    }

}