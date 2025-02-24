package com.empresa.aplicacion.domain

import android.net.Uri
import com.empresa.aplicacion.data.repository.ProblemasRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NuevoProblemaUseCase @Inject constructor(
    private val repository: ProblemasRepository

){
    suspend operator fun invoke(titulo: String, descripcion: String,  tipo: String, username: String,  imagenUri: Uri?){
        return withContext(IO) {
            repository.newProblema(titulo, descripcion, tipo, username, imagenUri)
        }
    }
}