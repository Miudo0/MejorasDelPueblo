package com.empresa.aplicacion.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.AplicacionTopAppBar

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToRegistro: () -> Unit,
) {
    Scaffold(
        topBar = {
            AplicacionTopAppBar(
                navigateToLogin = navigateToLogin
            )
        }

        ) { paddingValues ->
        LoginApp(
            navigateToHome = navigateToHome,
            paddingValues = paddingValues,
            navigateToRegistro = navigateToRegistro,

            )

    }
}


@Composable
fun LoginApp(
    navigateToHome: () -> Unit,
    navigateToRegistro: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: ValidarUSuarioViewModel = hiltViewModel()
) {

    var usuario by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    val errorMessage by rememberSaveable { mutableStateOf("") }

    val nombre by viewModel.state.collectAsState()
    val errorState by viewModel.errorMessageState.collectAsState()
    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        is ValidarUSuarioViewModel.ViewState.LoggedIn -> {
            Log.d("ComprobandoUsuario", "Usuario ha iniciado sesión")
            navigateToHome()
        }
        is ValidarUSuarioViewModel.ViewState.NotLoggedIn -> {
            Log.d("ComprobandoUsuario", "Usuario no ha iniciado sesión")
        }
        is ValidarUSuarioViewModel.ViewState.Loading -> {}
    }

    LaunchedEffect(Unit) {
        viewModel.navegacionState.collect { state ->
            when (state) {
                is ValidarUSuarioViewModel.NavigationState.NavigateToHome -> {
                    navigateToHome()
                }
            }
        }
    }

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

            onClick = { viewModel.getUsuario(usuario, pass) },
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
        when (nombre) {
            is ValidarUSuarioViewModel.databaseState.Error -> ErrorMensaje(errorMessage)
            is ValidarUSuarioViewModel.databaseState.Loading -> {}
            is ValidarUSuarioViewModel.databaseState.Success -> {}
        }
        when (val current = errorState) {
            is ValidarUSuarioViewModel.errorState.Success ->
                Text(
                    text = current.mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(top = 8.dp)
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
                    navigateToRegistro()
                }
        )
        MostrarApiScreen()
    }
}


//@Preview(showBackground = true)
//@Composable
//fun AppLoginPreview() {
//    AppTheme {
//        LoginScreen(navigateTo = {})
//    }
//
//}