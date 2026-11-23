package com.nicolascarmona.mytrainingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolascarmona.mytrainingapp.data.local.HistorialDao
import com.nicolascarmona.mytrainingapp.data.model.HistorialEjercicio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistorialViewModel (private val historialDao: HistorialDao) : ViewModel(){

    // Aquí guardamos el último registro del ejercicio actual para que la UI lo lea
    private val _ultimoRegistro = MutableStateFlow<HistorialEjercicio?>(null)
    val ultimoRegistro: StateFlow<HistorialEjercicio?> = _ultimoRegistro.asStateFlow()

    private val _historialCompleto = MutableStateFlow<List<HistorialEjercicio>>(emptyList())
    val historialCompleto: StateFlow<List<HistorialEjercicio>> = _historialCompleto.asStateFlow()

    // Llama a esta fdunción cada vez que cambies de ejercicio en la pantalla
    fun cargarUltimoRegistro(nombreEjercicio: String){
        viewModelScope.launch {
            // Recolectamos el Flow que nos devuelve Room
            historialDao.getUltimoRegistro(nombreEjercicio).collect { registro ->
                _ultimoRegistro.value = registro
            }
        }
    }

    // Guarda un nuevo registro en la base de datos
    fun guardarRegistro(
        nombreEjercicio: String,
        nuevasSeries: String,
        nuevasRepeticiones: String,
        nuevoPeso: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            val nuevoHistorial = HistorialEjercicio(
                nombreEjercicio = nombreEjercicio,
                series = nuevasSeries,
                repeticiones = nuevasRepeticiones,
                peso = nuevoPeso
            )
            historialDao.insertarRegistro(nuevoHistorial)

            // Al insertat, Room actualizará automáticamente el Flow y nuestra UI reaccionará
        }
    }

    fun cargarHistorialCompleto(nombreEjercicio: String) {
        viewModelScope.launch {
            historialDao.getHistorialCompleto(nombreEjercicio).collect { lista ->
                _historialCompleto.value = lista.filterNotNull()
            }
        }
    }
}