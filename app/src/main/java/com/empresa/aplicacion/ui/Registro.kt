package com.empresa.aplicacion.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R

@Composable
fun RegistroScreen(
    navigateTo: (String) -> Unit,
    viewModel: DatabaseViewModel = hiltViewModel()
) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val state by viewModel.state.collectAsState()
    val navigationState by viewModel.navegacionState.collectAsState()



    val datos = listOf(
        CajasDatos(
            username,
            { username = it },
            stringResource(R.string.usernameRegistro),
            stringResource(R.string.placeholder_registro_usuario)
        ),
        CajasDatos(
            password,
            { password = it },
            stringResource(R.string.pass_registro_usuario),
            stringResource(R.string.placeholder_registro_pass),
            PasswordVisualTransformation()
        ),
        CajasDatos(
            email,
            { email = it },
            stringResource(R.string.email_registro),
            stringResource(R.string.placeholder_registro_email)
        )
    )

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//
//        ) {
//        Text(
//            text = "Registro de Usuario",
//            style = MaterialTheme.typography.titleLarge,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        datos.forEach { dato ->
//            CajaTexto(
//                value = dato.value,
//                onValueChange = dato.onValueChange,
//                label = dato.label,
//                placeholder = dato.placeholder,
//                visualTransformation = dato.visualTransformation,
//                errorMessage = errorMessage
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        ErrorMensaje(errorMessage)
//
//        Button(
//            onClick = {
//
//                if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
//                    errorMessage = ""
//                    viewModel.insertarUsuario(username,password,email)
//                }
//            },
//
//
//
//            modifier = Modifier
//                .height(60.dp)
//        ) {
//
//            when (state){
//                is DatabaseViewModel.databaseState.Error -> ErrorMensaje(errorMessage)
//                is DatabaseViewModel.databaseState.Loading -> {}
//                is DatabaseViewModel.databaseState.Success -> {}
//            }
//
//
//           if (navigationState is DatabaseViewModel.NavigationState.NavigateToHome ){
//            navigateTo(Home.route)
//            }
//
//
//
//            Icon(
//                imageVector = Icons.Default.Done,
//                contentDescription = null
//            )
//            Spacer(
//                modifier = Modifier
//                    .width(8.dp)
//            )
//            Text(
//                text = stringResource(R.string.boton_registro),
//                style = MaterialTheme.typography.bodyMedium
//            )
//        }
//
//
//    }

}


//@Preview
//@Composable
//fun RegistroPreview() {
//    AppTheme {
//        RegistroScreen(
//
//        )
//    }

