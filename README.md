# MyTrainingApp

Aplicación móvil nativa para Android desarrollada en **Kotlin** y **Jetpack Compose** diseñada para gestionar rutinas de entrenamiento de fuerza y core, visualizar la ejecución técnica de cada ejercicio mediante animaciones y registrar el progreso histórico de cargas, series, repeticiones y tiempos.

---

## Características principales

* **Planificación estructurada por semanas y días:** Organización en ciclos de dos semanas (**Semana 1** y **Semana 2**) divididos en 4 sesiones específicas:
  * **Día 1:** Pectoral + Tríceps
  * **Día 2:** Espalda + Bíceps
  * **Día 3:** Pierna + Hombro
  * **Día 4:** Abdominales (rutinas cronometradas por tiempo)
* **Guía visual integrada:** Reproducción fluida de animaciones GIF locales para cada ejercicio mediante **Coil**, facilitando la correcta ejecución técnica.
* **Registro y edición de marcas en tiempo real:** Modificación rápida de series, repeticiones (o tiempo en segundos para ejercicios de core) y peso utilizado directamente desde la pantalla de detalle.
* **Historial de entrenamiento persistente:** Almacenamiento local en base de datos SQLite mediante **Room** para consultar el último registro guardado de cada ejercicio y su evolución histórica.
* **Interfaz adaptativa:** Diseño responsivo con soporte de desplazamiento vertical (*scroll*) y conservación de estado ante cambios de configuración y rotación de pantalla (`rememberSaveable`).

---

## Arquitectura y tecnologías utilizadas

* **Lenguaje:** Kotlin
* **Interfaz de usuario:** Jetpack Compose & Material Design 3
* **Arquitectura:** MVVM (*Model-View-ViewModel*) con flujo de datos reactivo (`StateFlow`)
* **Navegación:** Jetpack Navigation Compose
* **Persistencia local:** Room Database (Entities, DAO, Database)
* **Carga de imágenes y GIFs:** Coil (`coil-compose` y `coil-gif` con soporte para `ImageDecoderDecoder` y `GifDecoder`)

---

## Estructura del proyecto

```text
com.nicolascarmona.mytrainingapp
├── data
│   ├── local          # Configuración de Room (AppDatabase, Entities y DAOs)
│   ├── model          # Modelos de datos (Ejercicio, registros)
│   └── RutinasRepo.kt # Repositorio estático con las rutinas de Semana 1 y Semana 2
└── ui
    ├── navigation     # Grafo de navegación (AppNavigation)
    ├── screens        # Pantallas Compose (HomeScreen, WeekScreen, DetailScreen, HistorialScreen)
    ├── theme          # Paleta de colores, degradados y tipografía
    └── viewmodels     # Gestión de estado y comunicación con Room (HistorialViewModel)
