package com.empresa.aplicacion.ui.ProblemasSugerencias.Infraestructura

//
//@HiltViewModel
//class SharedViewModelInfraestructura  @Inject constructor(
//    private val mostrarProblemas: MostrarProblemasUseCase,
//) : ViewModel() {
//
//    private val _state = MutableStateFlow<InfraestructuraState>(InfraestructuraState.Loading)
//    val state: StateFlow<InfraestructuraState> = _state
//
//    // Función para cargar problemas
//    fun cargarProblemas() {
//        viewModelScope.launch {
//            try {
//                val problemasRegistrados = mostrarProblemas("Infraestructura")
//                _state.value = InfraestructuraState.Success(problemasRegistrados)
//            } catch (e: Throwable) {
//                _state.value = InfraestructuraState.Error("No hay nada")
//            }
//        }
//    }
//
//    // Función para actualizar los problemas (por ejemplo, después de una eliminación)
//    fun actualizarProblemas(listaActualizada: List<Problemas>) {
//        _state.value = InfraestructuraState.Success(listaActualizada)
//    }
//}
//
//sealed interface InfraestructuraState {
//    data class Success(val problemas: List<Problemas>) : InfraestructuraState
//    data class Error(val error: String) : InfraestructuraState
//    object Loading : InfraestructuraState
//
//}