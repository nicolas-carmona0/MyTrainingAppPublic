package com.nicolascarmona.mytrainingapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.ui.platform.LocalLocale
import com.nicolascarmona.mytrainingapp.data.model.HistorialEjercicio
import com.nicolascarmona.mytrainingapp.ui.viewmodels.HistorialViewModel

@Composable
fun HistorialScreen(
    nombreEjercicio: String,
    viewModel: HistorialViewModel,
    onBack: () -> Unit
) {
    val historial by viewModel.historialCompleto.collectAsState()

    // Cuando se abra la pantalla, le pedimos al ViewModel que cargue los datos
    LaunchedEffect(nombreEjercicio) {
        viewModel.cargarHistorialCompleto(nombreEjercicio)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(top = 26.dp)
    ) {
        // Cabecera
        Text(
            text = "Historial",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = nombreEjercicio,
            fontSize = 18.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Lista desplazable
        if (historial.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Aún no hay registros para este ejercicio.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(historial) { registro ->
                    TarjetaHistorial(registro)
                }
            }
        }

        // Botón volver
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Volver", color = Color.White)
        }
    }
}

@Composable
fun TarjetaHistorial(registro: HistorialEjercicio) {
    // Convertir el Long a una fecha legible
    val formato = SimpleDateFormat("dd MMM yyyy - HH:mm", LocalLocale.current.platformLocale)
    val fechaLegible = formato.format(Date(registro.fecha))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C)) // Fondo oscuro para la tarjeta
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = fechaLegible, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Series: ${registro.series}", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Reps: ${registro.repeticiones}", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Peso: ${registro.peso} kg", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}