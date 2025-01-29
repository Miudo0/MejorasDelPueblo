package com.empresa.aplicacion.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.navigation.Home
import com.empresa.aplicacion.ui.navigation.Registro
import com.empresa.aplicacion.ui.theme.AppTheme
import com.empresa.aplicacion.ui.ui.ApiViewModel
import com.empresa.aplicacion.ui.ui.DatabaseViewModel

@Composable
fun LoginScreen(navigateTo: (String) -> Unit) {
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
            paddingValues = paddingValues,

            )
    }

}


@Composable
fun LoginApp(
    navigateTo: (String) -> Unit,
    paddingValues: PaddingValues,
    viewModel: DatabaseViewModel = hiltViewModel()
) {

    var usuario by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val nombre by viewModel.state.collectAsState()

    val context = LocalContext.current

    val datosLogin = listOf(
        CajasDatos(
            value = usuario,
            onValueChange = { usuario = it },
            label = stringResource(R.string.usuario_login),
            placeholder = stringResource(R.string.placeholder_login_usuario)
        ),
        CajasDatos(
            value = pass,
            onValueChange = { pass = it },
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

        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Button(

            onClick = {

                if (usuario.isNotEmpty() && pass.isNotEmpty()) {
                    errorMessage = "" // Limpiar mensaje de error
                    viewModel.getUsuario(usuario, pass)
                }else{
                    errorMessage ="Ingrese usuario y contraseña"
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
                    navigateTo(Registro.route)
                }
        )
        LaunchedEffect(nombre) {
            if (nombre != null && nombre != "Usuario no encontrado") {
                // Usuario encontrado, navegar al Home
                navigateTo(Home.route)
            } else if (nombre == "Usuario no encontrado") {
                errorMessage = "Usuario o contraseña incorrectos"
            }
        }

        MostrarApi()
    }

}

//funcion para mostrar la api de chuck norris.
@Composable
fun MostrarApi(viewModel: ApiViewModel = hiltViewModel()) {
    val jokeString by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .padding(48.dp)
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Chiste de Chuck Norris",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = jokeString ?: "chiste no disponible",
            style = MaterialTheme.typography.bodySmall,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppLoginPreview() {
    AppTheme {
        LoginScreen(navigateTo = {})
    }

}