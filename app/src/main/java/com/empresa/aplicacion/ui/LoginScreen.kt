package com.empresa.aplicacion.ui

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
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.navigation.Home
import com.empresa.aplicacion.ui.navigation.Registro

@Composable
fun LoginScreen(
    navigateTo: (String) -> Unit,
    viewModel: ValidarUSuarioViewModel

) {


    Scaffold(
        topBar = {
            AplicacionTopAppBar(viewModel = viewModel)
        },

        ) { paddingValues ->
        LoginApp(
            navigateTo = navigateTo,
            paddingValues = paddingValues,
            viewModel = viewModel
        )
    }

}


@Composable
fun LoginApp(
    navigateTo: (String) -> Unit,
    paddingValues: PaddingValues,
    viewModel: ValidarUSuarioViewModel
) {

    var usuario by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    val errorMessage by rememberSaveable { mutableStateOf("") }

    val nombre by viewModel.state.collectAsState()
    val navigationState by viewModel.navegacionState.collectAsState()
    val errorState by viewModel.errorMessageState.collectAsState()

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
//        when (val current = errorState) {
//            is DatabaseViewModel.errorState.Success ->
//                Text(
//                    text = current.mensajeError,
//                    color = MaterialTheme.colorScheme.error,
//                    style = MaterialTheme.typography.bodySmall,
//                    modifier = Modifier
//                        .padding(top = 8.dp)
//                )
//        }
        //si meto el lauchedeffect no navega?

            when (navigationState) {
                is ValidarUSuarioViewModel.NavigationState.NavigateToHome -> navigateTo(Home.route)
                else -> {}
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