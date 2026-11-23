package com.nicolascarmona.mytrainingapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicolascarmona.mytrainingapp.data.local.AppDatabase
import com.nicolascarmona.mytrainingapp.ui.screens.HistorialScreen
import com.nicolascarmona.mytrainingapp.ui.viewmodels.HistorialViewModel
import com.nicolascarmona.mytrainingapp.ui.screens.DetailScreen
import com.nicolascarmona.mytrainingapp.ui.screens.HomeScreen
import com.nicolascarmona.mytrainingapp.data.RutinasRepo
import com.nicolascarmona.mytrainingapp.ui.screens.WeekScreen
import com.nicolascarmona.mytrainingapp.ui.theme.AppGradientBackground

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {

    // 1. Obtenemos el contexto y la base de datos
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val dao = database.historialDao()

    // 2. Creamos el ViewModel enseñándole cómo inyectar el DAO
    val historialViewModel: HistorialViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HistorialViewModel(dao) as T
            }
        }
    )

    CompositionLocalProvider(LocalContentColor provides Color.White) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .background(AppGradientBackground)
        ) {

            composable("home") {
                HomeScreen(
                    modifier = Modifier,
                    navController,
                    onNavigateToFirstWeek = { navController.navigate("w1") },
                    onNavigateToSecondWeek = { navController.navigate("w2") }
                )
            }
            composable("w1") {
                WeekScreen(
                    tituloSemana = "SEMANA 1",
                    onNavigateToHome = { navController.navigate("home") },
                    onNavigateToD1 = { navController.navigate("w1d1") },
                    onNavigateToD2 = { navController.navigate("w1d2") },
                    onNavigateToD3 = { navController.navigate("w1d3") },
                    onNavigateToD4 = { navController.navigate("w1d4") }

                    )
            }
            composable("w2") {
                WeekScreen(
                    tituloSemana = "SEMANA 2",
                    onNavigateToHome = { navController.navigate("home") },
                    onNavigateToD1 = { navController.navigate("w2d1") },
                    onNavigateToD2 = { navController.navigate("w2d2") },
                    onNavigateToD3 = { navController.navigate("w2d3") },
                    onNavigateToD4 = { navController.navigate("w2d4") })
            }
            composable("w1d1") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW1D1,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Pectoral - Semana 1" else "Tríceps - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }

            composable("w1d2") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW1D2,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Espalda - Semana 1" else "Bíceps - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }
            composable("w1d3") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW1D3,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Pierna - Semana 1" else "Hombro - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }
            composable("w1d4") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW1D4,
                    obtenerTitulo = { "Abdominales - Semana 1" },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    },
                    esDia4 = true
                )
            }
            composable("w2d1") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW2D1,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Pectoral - Semana 2" else "Tríceps - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }

            composable("w2d2") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW2D2,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Espalda - Semana 2" else "Bíceps - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }
            composable("w2d3") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW2D3,
                    obtenerTitulo = { index ->
                        if (index <= 5) "Pierna - Semana 2" else "Hombro - Semana 1"
                    },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    }
                )
            }
            composable("w2d4") {
                DetailScreen(
                    rutina = RutinasRepo.rutinaW2D4,
                    obtenerTitulo = { "Abdominales - Semana 2" },
                    onBack = { navController.popBackStack() },
                    viewModel = historialViewModel,
                    onNavigateToHistorial = { nombreEjercicio ->
                        navController.navigate("historial/$nombreEjercicio")
                    },
                    esDia4 = true
                )
            }
            composable("historial/{nombreEjercicio}") { backStackEntry ->
                // Extraemos el nombre del ejercicio de la ruta
                val nombre = backStackEntry.arguments?.getString("nombreEjercicio") ?: ""

                HistorialScreen(
                    nombreEjercicio = nombre,
                    viewModel = historialViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}