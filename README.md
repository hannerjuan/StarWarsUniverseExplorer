# Star Wars Universe Explorer 🌌

**Star Wars Universe Explorer** es una aplicación Android nativa desarrollada con **Kotlin** y **Jetpack Compose**. [cite_start]Su propósito es servir como una guía interactiva del universo Star Wars, consumiendo datos en tiempo real de la API pública [SWAPI](https://swapi.dev)[cite: 7].

[cite_start]Este proyecto implementa la arquitectura **MVVM** (Model-View-ViewModel) para garantizar un código limpio, modular y escalable[cite: 8].

## 📱 Características Principales

* [cite_start]**Exploración Completa:** Acceso a listados de Personajes, Películas, Planetas, Naves y Vehículos (consumo de +7 endpoints)[cite: 27].
* [cite_start]**Detalles Profundos:** Navegación desde listas generales a vistas detalladas (ej: ver un personaje y sus películas relacionadas)[cite: 15].
* [cite_start]**Búsqueda:** Funcionalidad para buscar y filtrar personajes por nombre[cite: 17].
* [cite_start]**Estados de UI:** Manejo visual de estados de Carga (Loading), Éxito (Success) y Error de red[cite: 18].
* **Interfaz Moderna:** UI construida 100% con Jetpack Compose.

## 🛠️ Tech Stack (Tecnologías)

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* [cite_start]**Arquitectura:** MVVM (Clean Architecture principles) [cite: 14]
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
* **Navegación:** Navigation Compose
* [cite_start]**Networking:** [Retrofit 2](https://square.github.io/retrofit/) + Gson Converter [cite: 13]
* **Imágenes:** [Coil](https://coil-kt.github.io/coil/) (Image Loading)
* **Asincronía:** Coroutines & Flow

## 🚀 Configuración e Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/hannerjuan/StarWarsUniverseExplorer.git](https://github.com/hannerjuan/StarWarsUniverseExplorer.git)
    ```
2.  **Abrir en Android Studio:**
    * Selecciona `File` > `Open` y busca la carpeta del proyecto.
3.  **Sincronizar Gradle:**
    * Espera a que se descarguen las dependencias.
4.  **Ejecutar:**
    * Conecta tu dispositivo físico (con Depuración USB activa) o usa un Emulador.

## 📂 Estructura del Proyecto

```text
com.example.starwarsuniverseexplorer
├── data
│   ├── model        # Modelos de datos (Data Classes)
│   ├── remote       # Configuración de API (Retrofit)
│   └── repository   # Lógica de obtención de datos
├── ui
│   ├── components   # Elementos UI reutilizables
│   ├── navigation   # Grafo de navegación
│   └── screens      # Pantallas (Home, Detail, etc.)
├── viewmodel        # State holders (Lógica de presentación)
└── utils            # Constantes y extensiones
