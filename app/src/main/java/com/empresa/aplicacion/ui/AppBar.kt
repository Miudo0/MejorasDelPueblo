package com.empresa.aplicacion.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empresa.aplicacion.R

//barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AplicacionTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.secondary
        ),
//        navigationIcon = {//agregar el icono en la parte derecha
//
//            IconButton(onClick = { /* No hacer nada por el momento */ }) {
//                Icon(
//                    Icons.Filled.Menu,
//                    contentDescription = "Menu",
//
//                    )
//            }
//        },
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

            ){
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
                    text =  "Usuario",
                    color = colorResource(id = R.color.white),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }


        },


//        actions = {//para colocarlos en el lado derecho
//            //boton para ir a la configuracion por ejemplo
//            IconButton(onClick = { /* No hacer nada por el momento */ }) {
//                Icon(
//                    Icons.Filled.AccountCircle,
//                    contentDescription = "Inicio",
//
//                    )//icono color blanco
//            }
//
//            IconButton(onClick = { /* No hacer nada por el momento */ }) {
//                Icon(
//                    Icons.Filled.MoreVert,
//                    contentDescription = "Opciones",
//
//                    )//icono color blanco
//            }
//        }
    )
}

@Composable
@Preview
fun AplicacionTopAppbarPreview() {
    AplicacionTopAppBar()
}