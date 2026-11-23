package com.nicolascarmona.mytrainingapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavHostController,
    onNavigateToFirstWeek: () -> Unit,
    onNavigateToSecondWeek: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("My Training App", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { onNavigateToFirstWeek() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF720000)
            )
        ) {
            Text("Semana 1", color = Color.White)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onNavigateToSecondWeek() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF720000)
            )
        ) {
            Text("Semana 2", color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
