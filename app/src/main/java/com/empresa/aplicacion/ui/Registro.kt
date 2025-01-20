package com.empresa.aplicacion.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.empresa.aplicacion.R
import com.empresa.aplicacion.data.AppDatabase.Companion.getDatabase
import com.empresa.aplicacion.data.Usuario
import com.empresa.aplicacion.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegistroScreen(navigateto: () -> Unit) {

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

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

        datos.forEach { dato ->
            CajaTexto(
                value = dato.value,
                onValueChange = dato.onValueChange,
                label = dato.label,
                placeholder = dato.placeholder,
                visualTransformation = dato.visualTransformation,
                errorMessage = errorMessage
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        ErrorMensaje(errorMessage)

        Button(
            onClick = {
                val db = getDatabase(context)
                val userDao = db.usuariosDao()


                if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                    errorMessage = ""
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val nuevoUsuario = Usuario(username = username, pass = password, email = email)
                            userDao.insertAll(nuevoUsuario)

                            withContext(Dispatchers.Main) {
                                navigateto()
                            }
                        }catch (e:Exception){
                            Log.e("RegistroScreen", "Error al registrarse", e)
                            withContext(Dispatchers.Main) {
                                errorMessage = "Error al registrarse"
                            }
                        }

                    }
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
                text = stringResource(R.string.boton_registro),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}






@Preview
@Composable
fun RegistroPreview() {
    AppTheme {
        RegistroScreen(
            navigateto = {}
        )
    }

}