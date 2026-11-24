# 💪 MyTrainingApp

MyTrainingApp es una aplicación nativa para Android desarrollada en Kotlin y Jetpack Compose diseñada para organizar rutinas de entrenamiento de fuerza y core por semanas y días, visualizar la técnica de cada ejercicio mediante animaciones locales y registrar el historial de cargas, series, repeticiones y tiempos.

---

## ✨ Características principales

Planificación estructurada por semanas y días: Organización en ciclos de dos semanas (Semana 1 y Semana 2) divididos en 4 sesiones específicas:

Día 1: Pectoral + Tríceps

Día 2: Espalda + Bíceps

Día 3: Pierna + Hombro

Día 4: Abdominales (rutinas cronometradas por tiempo)

Guía visual integrada: Reproducción fluida de animaciones GIF locales para cada ejercicio mediante Coil, facilitando la correcta ejecución técnica sin necesidad de conexión a internet.

Registro y edición de marcas en tiempo real: Diálogo unificado para actualizar rápidamente series, repeticiones (o tiempo en segundos para el Día 4) y peso utilizado directamente desde la pantalla de detalle.

Historial de progreso persistente: Almacenamiento local estructurado en Room que recupera automáticamente la última marca registrada de cada ejercicio y muestra su evolución histórica.

Interfaz adaptativa y segura: Diseño responsivo con soporte de desplazamiento vertical (verticalScroll), márgenes adaptados a recortes de cámara y barras del sistema (safeDrawingPadding), y conservación de estado ante rotación de pantalla (rememberSaveable).

---

## 🛠️ Stack Tecnológico y Arquitectura

El proyecto sigue las recomendaciones modernas de desarrollo en Android bajo el patrón arquitectónico MVVM (Model-View-ViewModel) y flujo de datos reactivo:

Lenguaje: Kotlin

Interfaz de Usuario: Jetpack Compose + Material Design 3

Navegación: Jetpack Navigation Compose (NavHost)

Persistencia Local: Room Database (Entities, DAO y AppDatabase) con compilación mediante KSP

Asincronía y Estado: Kotlin Coroutines & StateFlow (collectAsState)

Carga de Imágenes y GIFs: Coil (coil-compose y coil-gif con soporte para ImageDecoderDecoder y GifDecoder)

---

## 📁 Estructura del Proyecto

com.nicolascarmona.mytrainingapp
├── data/
│   ├── local/         # Configuración de Room (AppDatabase, Entities y DAOs)
│   ├── model/         # Modelos de datos (Ejercicio, registros de historial)
│   └── RutinasRepo.kt # Repositorio estático con las rutinas de Semana 1 y Semana 2
└── ui/
    ├── navigation/    # Grafo de navegación centralizado (AppNavigation)
    ├── screens/       # Pantallas Compose (HomeScreen, WeekScreen, DetailScreen, HistorialScreen)
    ├── theme/         # Paleta de colores, degradados y tema Material 3
    └── viewmodels/    # HistorialViewModel (gestión de estado y consultas a Room)

---

## 📄 Créditos y Atribuciones

Iconos de grupos musculares: Diseñados por cube29 a través de Flaticon bajo licencia gratuita con atribución.

Animaciones de ejercicios (GIFs): Recursos visuales procedentes de catálogos públicos de entrenamiento (ExerciseDB y recursos fitness abiertos), utilizados exclusivamente con fines educativos y de desarrollo de portafolio.
