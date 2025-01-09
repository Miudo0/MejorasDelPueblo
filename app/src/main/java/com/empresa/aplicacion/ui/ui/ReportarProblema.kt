package com.empresa.aplicacion.ui.ui

import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReportarScreen() {
    var descripcion by remember { mutableStateOf("") }
    var gravedad by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = "Reportes o Sugerencias",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = {
                Text(
                    text = "Describe el problema o sugerencia"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "Leve")
            RadioButton(
                selected = gravedad == 1,

                onClick = {
                    gravedad = 1
                }
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = "Moderado")
            RadioButton(
                selected = gravedad == 2,
                onClick = {
                    gravedad = 2
                }

            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = "Urgente")
            RadioButton(
                selected = gravedad == 3,
                onClick = {
                    gravedad = 3
                }
            )
        }
        Button(
            onClick = {}
        ) {
            Text(
                text = "Enviar"
            )
        }
    }

}

@Preview
@Composable
fun ReportarScreenPreview() {
    ReportarScreen()
}