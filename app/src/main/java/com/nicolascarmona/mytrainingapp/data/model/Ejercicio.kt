package com.nicolascarmona.mytrainingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Ejercicio(
    val nombre: String,
    val gif: Int,
    val series: Int,
    val repeticiones: Int? = null,
    val peso: Int? = null
)

@Entity(tableName = "historial_entrenamiento")
data class EjercicioDatos(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val peso: Int? = null,
    val repeticiones: Int? = null,
    val fecha: Long
)