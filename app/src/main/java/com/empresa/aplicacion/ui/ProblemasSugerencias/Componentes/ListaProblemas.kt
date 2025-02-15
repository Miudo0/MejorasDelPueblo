package com.empresa.aplicacion.ui.ProblemasSugerencias.Componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.domain.Problema

@Composable
fun ProblemasLista(
    problemas: List<Problema>,
    deleteProblema: (Problema) -> Unit,
    paddingValues: PaddingValues,
    marcarProblemaSolucionado: (Problema) -> Unit,
    modifier: Modifier = Modifier

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(problemas) { problema ->
            CartaItem(
                problema,
                onDelete = { deleteProblema(problema) },
                marcarProblemaSolucionado = { marcarProblemaSolucionado(problema) }

            )

        }
    }
}