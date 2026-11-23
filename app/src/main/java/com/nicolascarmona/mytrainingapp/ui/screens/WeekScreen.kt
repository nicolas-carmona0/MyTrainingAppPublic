package com.nicolascarmona.mytrainingapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascarmona.mytrainingapp.R

@Composable
fun WeekScreen(
    tituloSemana: String,
    onNavigateToD1: () -> Unit,
    onNavigateToD2: () -> Unit,
    onNavigateToD3: () -> Unit,
    onNavigateToD4: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(tituloSemana, fontWeight = FontWeight.Bold, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // DÍA 1
        Text("DÍA 1", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_pecs),
                contentDescription = "Pectoral"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_triceps),
                contentDescription = "Tríceps"
            )
        }
        Button(
            onClick = { onNavigateToD1() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Pectoral + Tríceps", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DÍA 2
        Text("DÍA 2", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_back),
                contentDescription = "Espalda"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_biceps),
                contentDescription = "Bíceps"
            )
        }
        Button(
            onClick = { onNavigateToD2() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Espalda + Bíceps", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DÍA 3
        Text("DÍA 3", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_leg),
                contentDescription = "Pierna"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_shoulder),
                contentDescription = "Hombro"
            )
        }
        Button(
            onClick = { onNavigateToD3() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Pierna + Hombro", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // DÍA 4
        Text("DÍA 4", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.icon_abs),
                contentDescription = "Abdominales"
            )
        }
        Button(
            onClick = { onNavigateToD4() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Abdominales", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToHome() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF720000))
        ) {
            Text("Home", color = Color.White)
        }
    }
}