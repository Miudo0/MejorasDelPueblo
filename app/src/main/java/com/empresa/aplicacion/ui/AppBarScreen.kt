package com.empresa.aplicacion.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.R
import com.empresa.aplicacion.ui.Login.ValidarUSuarioViewModel

//barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplicacionTopAppBar(
    viewModel: ValidarUSuarioViewModel

    ) {
    val username by viewModel.username.collectAsState()

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
                )
            }


        },


        )
}

//@Composable
//@Preview
//fun AplicacionTopAppbarPreview() {
//    AplicacionTopAppBar(username = "Invitado")
//}