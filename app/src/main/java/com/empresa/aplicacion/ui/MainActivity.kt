package com.empresa.aplicacion.ui


import android.Manifest
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.empresa.aplicacion.ui.navigation.NavigationWrapper
import com.example.compose.AppTheme


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val TAG = this.javaClass.simpleName//MainActivity



    //para darle el permiso a la camara.
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.CAMERA] == true &&
            permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true &&
            permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
            // Permisos concedidos
        } else {
            // Permisos no concedidos
        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: La aplicación se ha creado.")


        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    requestPermissions()
                    NavigationWrapper()

                }
            }
        }
        val app = application as MejorasDelpueblo
        app.leerDatos()
        app.actualizarPass("Admin", "3333")
        app.leerDatos()

//         app.borrarDB()

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: La aplicación se ha iniciado.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: La aplicación se ha reanudado.")


    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: La aplicación se ha pausado.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: La aplicación se ha detenido.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: La aplicación se ha destruido.")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: La aplicación se ha guardado.")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        Log.d(TAG, "onRestoreInstanceState: La aplicación se ha restaurado.")
    }

    @Deprecated("Deprecated in Java")
    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory: La aplicación  ha agotado la memoria.")
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "Rotación detectada: Modo horizontal")
        } else if (newConfig.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "Rotación detectada: Modo vertical")
        }
    }


}



