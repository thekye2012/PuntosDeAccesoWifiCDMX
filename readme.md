# Documentación de la API de Puntos de Acceso WiFi

Esta API permite interactuar con una base de datos de puntos de acceso WiFi, ofreciendo funcionalidades para obtener listas de puntos de acceso, buscar por ID, encontrar los más cercanos a una ubicación, y filtrar por alcaldía.

## Configuración e Instalación

Para configurar e iniciar el entorno de desarrollo y pruebas de esta API, sigue los pasos detallados a continuación:

1. **Clonar el Repositorio**

   Clona el repositorio usando el siguiente comando:
   ```
    git clone https://github.com/thekye2012/PuntosDeAccesoWifiCDMX.git
    ```

2. **Abrir el Proyecto en Intellij IDEA**

Abre el proyecto clonado en Intellij IDEA o IDE De tu preferencia para comenzar a trabajar en él.

3. **Configuración de la Red del Servidor Docker**

Si la red de tu servidor Docker está personalizada, debes ingresar al archivo `application.yml` y modificar el `url` para el servidor MongoDB.

5. **Ejecución del Archivo `docker-compose.yml`**

Ejecuta el archivo `docker-compose.yml`. En caso de que la ejecución de `docker-compose` falle en el servicio "puntosdeacceso", sigue estos pasos:
- Ejecuta `mvn clean` y luego `mvn package` en la carpeta del proyecto.
- Intenta iniciar nuevamente el servicio ejecutando el `docker-compose`.
- Si el problema persiste, verifica que tu base de datos MongoDB esté correctamente configurada.

6. **Acceso a la Documentación Swagger**

Una vez iniciado el servicio, ingresa a `http://localhost:3000/swagger-ui/index.html` para ver la documentación Swagger y probar los endpoints disponibles.


## API REST Endpoints

### Obtener Todos los Puntos de Acceso WiFi

- **Endpoint:** `/api/wifi`
- **Método:** GET
- **Descripción:** Retorna una lista paginada de todos los puntos de acceso WiFi disponibles.
- **Parámetros de consulta:**
    - `page`: Número de página.
    - `size`: Tamaño de página.
    - `sort`: Campo por el cual ordenar.
- **Ejemplo de solicitud:**
  ```
  GET /api/wifi?page=0&size=10&sort=id
  ```
- **Ejemplo de respuesta:**
  ```json
  {
    "totalPages": 5,
    "totalElements": 50,
    "size": 10,
    "content": [
      {
        "id": "1",
        "programa": "Programa WiFi Ciudad",
        "fechaInstalacion": "2020-01-01",
        "latitud": 19.432608,
        "longitud": -99.133209,
        "colonia": "Centro",
        "alcaldia": "Cuauhtémoc"
      }
    ],
    "number": 0,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageable": {
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "numberOfElements": 10,
    "first": true,
    "last": false,
    "empty": false
  }
  ```

### Obtener Punto de Acceso WiFi por ID

- **Endpoint:** `/api/wifi/{id}`
- **Método:** GET
- **Descripción:** Obtiene los detalles de un punto de acceso WiFi específico por su ID.
- **Parámetros de ruta:**
    - `id`: ID del punto de acceso WiFi.
- **Ejemplo de solicitud:**
  ```
  GET /api/wifi/1
  ```
- **Ejemplo de respuesta:**
  ```json
  {
    "id": "1",
    "programa": "Programa WiFi Ciudad",
    "fechaInstalacion": "2020-01-01",
    "latitud": 19.432608,
    "longitud": -99.133209,
    "colonia": "Centro",
    "alcaldia": "Cuauhtémoc"
  }
  ```

### Obtener Puntos de Acceso WiFi Más Cercanos

- **Endpoint:** `/api/wifi/nearest`
- **Método:** GET
- **Descripción:** Encuentra los puntos de acceso WiFi más cercanos a una ubicación específica.
- **Parámetros de consulta:**
    - `latitude`: Latitud de la ubicación.
    - `longitude`: Longitud de la ubicación.
    - `page`: Número de página.
    - `size`: Tamaño de página.
    - `sort`: Campo por el cual ordenar.
- **Ejemplo de solicitud:**
  ```
  GET /api/wifi/nearest?latitude=19.432608&longitude=-99.133209&page=0&size=5&sort=id
  ```
- **Ejemplo de respuesta:**
  ```json
  {
    "totalPages": 1,
    "totalElements": 5,
    "size": 5,
    "content": [
      {
        "id": "2",
        "programa": "Programa WiFi Parques",
        "fechaInstalacion": "2021-05-10",
        "latitud": 19.432607,
        "longitud": -99.133208,
        "colonia": "Roma Norte",
        "alcaldia": "Cuauhtémoc"
      }
    ],
    "number": 0,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageable": {
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 5,
      "paged": true,
      "unpaged": false
    },
    "numberOfElements": 5,
    "first": true,
    "last": false,
    "empty": false
  }
  ```

### Filtrar Puntos de Acceso WiFi por Alcaldía

- **Endpoint:** `/api/wifi/by-alcaldia/{alcaldia}`
- **Método:** GET
- **Descripción:** Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía.
- **Parámetros de ruta:**
    - `alcaldia`: Nombre de la alcaldía para filtrar.
- **Parámetros de consulta:**
    - `page`: Número de página.
    - `size`: Tamaño de página.
    - `sort`: Campo por el cual ordenar.
- **Ejemplo de solicitud:**
  ```
  GET /api/wifi/by-alcaldia/Cuauhtémoc?page=0&size=10&sort=id
  ```
- **Ejemplo de respuesta:**
  ```json
  {
    "totalPages": 2,
    "totalElements": 15,
    "size": 10,
    "content": [
      {
        "id": "3",
        "programa": "Programa WiFi Comunitario",
        "fechaInstalacion": "2019-09-15",
        "latitud": 19.426726,
        "longitud": -99.142897,
        "colonia": "Condesa",
        "alcaldia": "Cuauhtémoc"
      }
    ],
    "number": 0,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageable": {
      "offset": 0,
      "pageNumber": 0,
      "pageSize": 10,
      "paged": true,
      "unpaged": false
    },
    "numberOfElements": 10,
    "first": true,
    "last": false,
    "empty": false
  }
  ```

# Documentación de la API GraphQL para Puntos de Acceso WiFi

Esta API GraphQL permite interactuar con datos sobre puntos de acceso WiFi, incluyendo operaciones para consultar listas de puntos de acceso, obtener detalles por ID, y más.

## Endpoint GraphQL

- **URL GraphQL:** `http://localhost:3000/graphql`
- **Interfaz Interactiva (GraphiQL):** `http://localhost:3000/graphiql`

## Consultas Disponibles

### Obtener Todos los Puntos de Acceso WiFi

**Query:**
```graphql
query {
  getAllWifi(page: 0, size: 10) {
    content {
      id
      programa
      fechaInstalacion
      latitud
      longitud
      colonia
      alcaldia
    }
    totalPages
    totalElements
    size
    number
  }
}
```

**Ejemplo de respuesta:**
```json
{
  "data": {
    "getAllWifi": {
      "content": [
        {
          "id": "1",
          "programa": "Programa WiFi Ciudad",
          "fechaInstalacion": "2020-01-01",
          "latitud": 19.432608,
          "longitud": -99.133209,
          "colonia": "Centro",
          "alcaldia": "Cuauhtémoc"
        }
      ],
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0
    }
  }
}
```

### Obtener Punto de Acceso WiFi por ID

**Query:**
```graphql
query {
  getWifiById(id: "1") {
    id
    programa
    fechaInstalacion
    latitud
    longitud
    colonia
    alcaldia
  }
}
```

**Ejemplo de respuesta:**
```json
{
  "data": {
    "getWifiById": {
      "id": "1",
      "programa": "Programa WiFi Ciudad",
      "fechaInstalacion": "2020-01-01",
      "latitud": 19.432608,
      "longitud": -99.133209,
      "colonia": "Centro",
      "alcaldia": "Cuauhtémoc"
    }
  }
}
```


### Filtrar Puntos de Acceso WiFi por Alcaldía

**Query:**
```graphql
query {
  getWifiByAlcaldia(alcaldia: "Cuauhtémoc", page: 0, size: 10) {
    content {
      id
      programa
      fechaInstalacion
      latitud
      longitud
      colonia
      alcaldia
    }
    totalPages
    totalElements
    size
    number
  }
}
```

**Ejemplo de respuesta:**
```json
{
  "data": {
    "getWifiByAlcaldia": {
      "content": [
        {
          "id": "2",
          "programa": "Programa WiFi Parques",
          "fechaInstalacion": "2021-05-10",
          "latitud": 19.432607,
          "longitud": -99.133208,
          "colonia": "Roma Norte",
          "alcaldia": "Cuauhtémoc"
        }
      ],
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0
    }
  }
}
```

### Obtener Puntos de Acceso WiFi por Ubicación

**Query:**
```graphql
query {
  getWifiByUbicacion(latitud: 19.4326, longitud: -99.1332, page: 0, size: 10) {
    content {
      id
      programa
      fechaInstalacion
      latitud
      longitud
      colonia
      alcaldia
    }
    totalPages
    totalElements
    size
    number
  }
}
```

**Ejemplo de respuesta:**
```json
{
  "data": {
    "getWifiByUbicacion": {
      "content": [
        {
          "id": "3",
          "programa": "Programa WiFi Comunitario",
          "fechaInstalacion": "2019-09-15",
          "latitud": 19.426726,
          "longitud": -99.142897,
          "colonia": "Condesa",
          "alcaldia": "Cuauhtémoc"
        }
      ],
      "totalPages": 1,
      "totalElements": 1,
      "size": 10,
      "number": 0
    }
  }
}
```
Prueba Tecnica para Desarrollador Backend - Java - MongoDB - GraphQL - REST API - Docker - Swagger - Documentación - Gerardo Guzman Chavez


