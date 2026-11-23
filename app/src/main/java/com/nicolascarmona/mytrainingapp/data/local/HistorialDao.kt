package com.nicolascarmona.mytrainingapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nicolascarmona.mytrainingapp.data.model.HistorialEjercicio
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialDao{

    // Guardar un nuevo registro tras darle a "Confirmar" en el dialog
    @Insert
    fun insertarRegistro(historial: HistorialEjercicio)

    // Obtener SOLO el último registro de un ejercicio específico para mostrarlo en pantalla
    @Query("SELECT * FROM historial_ejercicios WHERE nombreEjercicio = :nombre ORDER BY fecha DESC LIMIT 1")
    fun getUltimoRegistro(nombre: String): Flow<HistorialEjercicio?>

    // Obtener todo el historial de un ejercicio
    @Query("SELECT * FROM historial_ejercicios WHERE nombreEjercicio = :nombre ORDER BY fecha DESC")
    fun getHistorialCompleto(nombre: String): Flow<List<HistorialEjercicio?>>
}