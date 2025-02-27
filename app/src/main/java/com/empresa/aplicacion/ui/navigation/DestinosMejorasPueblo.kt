package com.empresa.aplicacion.ui.navigation

import com.empresa.aplicacion.R


//creo la interfaz de desetinos para las mejoras del pueblo
interface DestinosMejorasPueblo {
    val image: Int
    val route: String


}

object Home : DestinosMejorasPueblo {
    override val image = R.drawable.homeicon
   override val route = "Inicio"


}

object ProblemasSugerencias : DestinosMejorasPueblo {
   override val image = R.drawable.reporteproblemasicono
    override val route = "Tablon de problemas"
}

object RegistroUsuario : DestinosMejorasPueblo{
    override val image = R.drawable.proyectoscomunitariosicono
    override val route = "Tus reportes"
}

object BotonesAcceso {
    val route: String = "botonesAcceso"
}
object Login {
    val route: String = "loginScreen"
}
object Registro {
    val route: String = "registroScreen"
}

object Titulo {
    val route: String = "tituloScreen"
}
//Rutas para problemas y sugerencias
object ReportarProblema {
    val route: String = "reportarProblemaScreen"
}

object Infraestructura {
    val route: String = "infraestructuraScreen"
}
object Trafico{
    val route: String = "traficoScreen"
}
object MedioAmbiente{
    val route: String = "medioAmbienteScreen"
}
object Seguridad{
    val route: String = "seguridadScreen"
}




//lista de las pantallas
var destinosMejoras = listOf(Home, ProblemasSugerencias, RegistroUsuario)

