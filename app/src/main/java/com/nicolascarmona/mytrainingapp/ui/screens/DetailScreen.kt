package com.nicolascarmona.mytrainingapp.ui.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.nicolascarmona.mytrainingapp.data.model.Ejercicio
import com.nicolascarmona.mytrainingapp.ui.viewmodels.HistorialViewModel

@Composable
fun DetailScreen(
    rutina: List<Ejercicio>,
    obtenerTitulo: (Int) -> String,
    onBack: () -> Unit,
    viewModel: HistorialViewModel,
    onNavigateToHistorial: (String) -> Unit,
    esDia4: Boolean = false
) {
    var numeroEjercicio by rememberSaveable { mutableIntStateOf(0) }

    val ejercicioActual = rutina[numeroEjercicio]

    // Observamos el último registro desde el ViewModel
    val ultimoRegistro by viewModel.ultimoRegistro.collectAsState()

    // Efecto que se dispara cada vez que cambia el numeroEjercicio
    LaunchedEffect(numeroEjercicio) {
        viewModel.cargarUltimoRegistro(ejercicioActual.nombre)
    }

    // Único estado para controlar el nuevo diálogo unificado
    var mostrarDialogo by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = obtenerTitulo(numeroEjercicio),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(rutina[numeroEjercicio].nombre, fontSize = 16.sp)

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(rutina[numeroEjercicio].gif)
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Ejercicio",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { mostrarDialogo = true }
                .padding(vertical = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Series")
                Text(ultimoRegistro?.series ?: "${ejercicioActual.series}")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(if (esDia4) "Tiempo" else "Repeticiones")
                Text(ultimoRegistro?.repeticiones ?: ejercicioActual.repeticiones.toString())
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Peso")
                Text(ultimoRegistro?.peso ?: "${ejercicioActual.peso}")
            }
        }

        if (mostrarDialogo) {
            DialogoModificacionCompleto(
                titulo = "Modificar Ejercicio",
                seriesIniciales = ultimoRegistro?.series ?: ejercicioActual.series.toString(),
                repsIniciales = ultimoRegistro?.repeticiones
                    ?: ejercicioActual.repeticiones.toString(),
                pesoInicial = ultimoRegistro?.peso ?: ejercicioActual.peso.toString(),
                esDia4 = esDia4,
                onConfirmar = { s, r, p ->
                    viewModel.guardarRegistro(
                        nombreEjercicio = ejercicioActual.nombre,
                        nuevasSeries = s,
                        nuevasRepeticiones = r,
                        nuevoPeso = p
                    )
                    mostrarDialogo = false
                },
                onCancelar = { mostrarDialogo = false }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (numeroEjercicio == 0) onBack() else numeroEjercicio--
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
            ) {
                Text("Anterior", color = Color.White)
            }
            Button(
                onClick = {
                    if (numeroEjercicio == rutina.size - 1) onBack() else numeroEjercicio++
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
            ) {
                Text("Siguiente", color = Color.White)
            }
        }

        Text("Ejercicio ${numeroEjercicio + 1} de ${rutina.size}")

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToHistorial(ejercicioActual.nombre) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Historial", color = Color.White)
        }
    }
}

@Composable
fun DialogoModificacionCompleto(
    titulo: String,
    seriesIniciales: String,
    repsIniciales: String,
    pesoInicial: String,
    esDia4: Boolean = false,
    onConfirmar: (series: String, reps: String, peso: String) -> Unit,
    onCancelar: () -> Unit
) {
    var series by rememberSaveable { mutableStateOf(seriesIniciales) }
    var reps by rememberSaveable { mutableStateOf(repsIniciales) }
    var peso by rememberSaveable { mutableStateOf(pesoInicial) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(titulo) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = series,
                    onValueChange = { series = it },
                    label = { Text("Series") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = reps,
                    onValueChange = { reps = it },
                    label = { Text(if (esDia4) "Tiempo (s)" else "Repeticiones") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = peso,
                    onValueChange = { peso = it },
                    label = { Text("Peso (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirmar(series, reps, peso) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
            ) {
                Text("Confirmar", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancelar,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
            ) {
                Text("Cancelar", color = Color.White)
            }
        }
    )
}