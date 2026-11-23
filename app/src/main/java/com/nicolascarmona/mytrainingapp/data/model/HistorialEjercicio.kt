package com.nicolascarmona.mytrainingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historial_ejercicios")
data class HistorialEjercicio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreEjercicio: String,    // Usaremos el nombre para vincularlo a la RutinasRepo
    val fecha: Long = System.currentTimeMillis(),   // Para saber cuándo se hizo
    val peso: String,
    val series: String,
    val repeticiones: String
)