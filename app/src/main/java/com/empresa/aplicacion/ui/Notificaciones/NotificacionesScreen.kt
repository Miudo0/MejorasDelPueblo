package com.empresa.aplicacion.ui.Notificaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.empresa.aplicacion.ui.AplicacionBottomAppBar
import com.empresa.aplicacion.ui.AplicacionTopAppBar
import com.empresa.aplicacion.ui.navigation.NotificacionesScreen
import com.empresa.aplicacion.ui.navigation.destinosMejoras

@Composable
fun NotificationsScreen(

    navigateTo: (String) -> Unit,

    ) {
        Scaffold(
            topBar = {
                AplicacionTopAppBar(navigateToLogin = navigateTo, navigateToHome = navigateTo)
            },
            bottomBar = {
                AplicacionBottomAppBar(
                    allScreens = destinosMejoras,
                    onTabSelected = { ruta ->
                        navigateTo(ruta.route)
                    },
                    currentScreen = NotificacionesScreen
                )
            }
        ) { paddingValues ->
            AppContent(
                navigateTo = navigateTo,
                paddingValues = paddingValues
            )
        }
    }
@Composable
fun AppContent(
    navigateTo: (String) -> Unit,
    paddingValues: PaddingValues
) {
    Column(modifier = Modifier
        .padding(paddingValues)
    ){
        Text(text = "Notificaciones")
    }
}

//@Composable
//@Preview
//fun PreviewNotificationsScreen() {
//    AppTheme {
//        NotificationsScreen(
//            navigateTo = {},
//            viewModel = viewModel()
//        )
//    }
//}
