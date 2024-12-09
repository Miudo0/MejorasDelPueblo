package com.empresa.aplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empresa.aplicacion.ui.theme.SaulTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaulTheme {
                BaseApp()
            }
        }
    }
}

// Estructura para las cartas
data class Carta(
    val titulo: String,
    val descripcion: String,
    val color: Color,
    val imagen: Painter
)

@Composable
fun App(paddingValues: PaddingValues = PaddingValues()) {//vista de la app con el padding
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Mejoras", "Configuración")

    //variables donde meto las imagnes
    val fondo = painterResource(id = R.drawable.fondo2)

    val imgProblemas = painterResource(id = R.drawable.reporteproblemas)
    val imgProyectos = painterResource(id = R.drawable.proyectoscomunitarios)
    val imgNotificaciones = painterResource(id = R.drawable.notificaciones)
    val imgVoluntariado = painterResource(id = R.drawable.voluntariado)

    Box(
        modifier = Modifier//caja para poner el fondo con los padding para que no se quede debajo
            .fillMaxSize()
            .padding(paddingValues)
    )
    {
        Image(
            painter = fondo,
            contentDescription = null,
            contentScale = ContentScale.Crop,//rellenar completamente con la imagen

            modifier = Modifier.fillMaxSize(),

            )
        Column(Modifier.fillMaxWidth()) {

//informacion:
            //https://m3.material.io/components/cards/overview
            //https://developer.android.com/develop/ui/compose/components/card?hl=es-419#elevated
// creo cada una de las cartas; cambie de como la tenia para que fueran una lista
            val listaCartas = listOf(
                Carta(
                    titulo = stringResource(R.string.reporte_de_problemas_y_sugerencias),//meto el texto en xml
                    descripcion = stringResource(R.string.informa),
                    color = Color(0xFF877EBF),
                    imagen = imgProblemas
                ),
                Carta(
                    titulo = stringResource(R.string.proyectos_comunitarios),
                    descripcion = stringResource(R.string.texto_proyectos),
                    color = Color(0xFF5881A6),
                    imagen = imgProyectos
                ),
                Carta(
                    titulo = stringResource(R.string.notificaciones_e_informaci_n_relevante),
                    descripcion = stringResource(R.string.texto_notificaciones),
                    color = Color(0xFFBF755A),
                    imagen = imgNotificaciones
                ),
                Carta(
                    titulo = stringResource(R.string.voluntariado_y_ayudas),
                    descripcion = stringResource(R.string.texto_voluntariado),
                    color = Color(0xFFF2AD94),
                    imagen = imgVoluntariado
                )
            )


            Column(Modifier.fillMaxWidth()) {
                listaCartas.forEach { card ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = card.color
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        Image(
                            painter = card.imagen,
                            contentDescription = "Imagen adicional",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Text(
                            text = card.titulo,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                        Text(
                            text = card.descripcion,
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        )
                    }
                }
            }
        }
    }


}


//vista previa
@Preview(showBackground = true)
@Composable
fun Previsualizacion() {
    SaulTheme {
        BaseApp()

    }
}

//base de la app con el Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseApp() {
    Scaffold(
        //creo la barra superior de la aplicacion
        topBar = {
            TopAppBar(//Para ponerle botones

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black.copy(alpha = 0.8f),
                    titleContentColor = Color.White,
                ),
                navigationIcon = {//agregar el icono en la parte derecha

                    IconButton(onClick = { /* No hacer nada por el momento */ }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                title = {

                    Text(
                        text = stringResource(R.string.titulo_aplicacion),
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),

                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center


                    )

                },


                actions = {//para colocarlos en el lado derecho
                    //boton para ir a la configuracion por ejemplo
                    IconButton(onClick = { /* No hacer nada por el momento */ }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Inicio",
                            tint = Color.White
                        )//icono color blanco
                    }

                    IconButton(onClick = { /* No hacer nada por el momento */ }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "Opciones",
                            tint = Color.White
                        )//icono color blanco
                    }
                }
            )
        },//fin de la barra superior

        bottomBar = {  // Agregamos el BottomAppBar aquí
            BottomAppBar(
                modifier = Modifier,

                containerColor = Color.Black.copy(alpha = 0.8f),
                contentColor = Color.White

            ) {
                IconButton(onClick = { /* Acción para el botón de inicio en el BottomAppBar */ }) {
                    Icon(
                        Icons.Filled.Home,
                        contentDescription = "Inicio",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el siguiente botón
                IconButton(onClick = { /* Acción para el botón de configuración */ }) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Configuración",
                        tint = Color.White
                    )
                }
            }

        },

        content = { paddingValues ->
            App(paddingValues = paddingValues)//llamamos a la vista App con los valores de padding

        }

    )//fin del scaffold
}
