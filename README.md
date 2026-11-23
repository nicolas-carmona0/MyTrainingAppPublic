# 🏋️‍♂️ MyTrainingApp

**MyTrainingApp** es una aplicación nativa para Android desarrollada en **Kotlin** con **Jetpack Compose**, diseñada para guiar y registrar el progreso en rutinas de entrenamiento de fuerza divididas por semanas y grupos musculares.

---

## ✨ Características Principales

* **Planificación Estructurada:** Rutinas organizadas por semanas (**Semana 1** y **Semana 2**) y divididas en 3 días de entrenamiento por bloques musculares:
  * **Día 1:** Pectoral + Tríceps
  * **Día 2:** Espalda + Bíceps
  * **Día 3:** Pierna + Hombro
* **Guía Visual Animada:** Cada ejercicio cuenta con su propio GIF demostrativo cargado de forma fluida mediante **Coil** para asegurar una correcta ejecución técnica.
* **Registro y Edición de Marcas:** Posibilidad de modificar dinámicamente las **series**, **repeticiones** y **peso (kg)** de cada ejercicio mediante un diálogo interactivo.
* **Persistencia Local (Offline-first):** Almacenamiento local mediante **Room Database**, recordando automáticamente la última marca registrada para cada ejercicio.
* **Historial de Progreso:** Pantalla dedicada por ejercicio que muestra de forma cronológica todas las marcas guardadas con fecha y hora exacta.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **Interfaz de Usuario:** [Jetpack Compose](https://developer.android.com/jetpack/compose) & Material Design 3
* **Arquitectura:** MVVM (Model - View - ViewModel) con flujos reactivos (`StateFlow` y `Flow`)
* **Navegación:** [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
* **Base de Datos Local:** [Room](https://developer.android.com/training/data-storage/room) (SQLite)
* **Carga de Imágenes / GIFs:** [Coil](https://coil-kt.github.io/coil/) (`GifDecoder` / `ImageDecoderDecoder`)
* **Asincronía:** Kotlin Coroutines

---

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura limpia separando la capa de datos de la interfaz de usuario:

```text
com.nicolascarmona.mytrainingapp
├── data
│   ├── local
│   │   ├── AppDatabase.kt          # Configuración de la instancia Singleton de Room
│   │   └── HistorialDao.kt         # Consultas SQL (Insertar y obtener historial)
│   ├── model
│   │   ├── Ejercicio.kt            # Modelo de datos base de cada ejercicio
│   │   └── HistorialEjercicio.kt   # Entidad de Room para la tabla historial_ejercicios
│   └── RutinasRepo.kt              # Repositorio estático con todas las rutinas y recursos
├── ui
│   ├── navigation
│   │   └── Navigation.kt           # Grafo de navegación (NavHost) e inyección del ViewModel
│   ├── screens
│   │   ├── HomeScreen.kt           # Pantalla principal de selección de semana
│   │   ├── WeekScreen.kt           # Selección de días y grupos musculares
│   │   ├── DetailScreen.kt         # Detalle del ejercicio, GIF y edición de marcas
│   │   └── HistorialScreen.kt      # Listado histórico de marcas por ejercicio
│   ├── theme                       # Tema, colores y estilos de la aplicación
│   └── viewmodels
│       └── HistorialViewModel.kt   # Gestión del estado de UI y comunicación con Room
└── MainActivity.kt                 # Punto de entrada de la aplicación
