package com.empresa.aplicacion.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.navigation.Login

//barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplicacionTopAppBar(
    navigateTo: (String) -> Unit,
    viewModel: AppbarViewModel = hiltViewModel()

    ) {
    val username by viewModel.username.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val navigationState by viewModel.navegacionState.collectAsState()

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.secondary
        ),

        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(R.string.titulo_aplicacion),
                    color = colorResource(id = R.color.white),
                    modifier = Modifier
                        .padding(16.dp),


                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = username,
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Continuar") },
                        onClick = { expanded = false } // No hace nada, solo cierra el menú
                    )
                    DropdownMenuItem(
                        text = { Text("Cerrar sesión") },
                        onClick = {
                            expanded = false
                            viewModel.logout()
                        }
                    )
                }
            }
        },
    )
    when (navigationState) {
        is AppbarViewModel.NavigationStateCerrarSesion.NavigateToLogin -> navigateTo(Login.route)
        else -> {}
    }
}

//@Composable
//@Preview
//fun AplicacionTopAppbarPreview() {
//    AplicacionTopAppBar(username = "Invitado")
//}