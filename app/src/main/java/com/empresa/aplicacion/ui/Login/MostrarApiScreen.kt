package com.empresa.aplicacion.ui.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
    fun MostrarApiScreen(viewModel: MostrarApiViewModel = hiltViewModel()) {
        val jokeString by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .padding(48.dp)
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chiste de Chuck Norris",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(16.dp))


            when (val current = jokeString) {
                is ApiState.Loading -> LinearProgressIndicator()
                is ApiState.Success -> Text(text = current.joke)
                is ApiState.Error -> Text(text = "No se puede cargar el chiste")
            }
        }
    }
