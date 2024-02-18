# Documentación de la API de Puntos de Acceso WiFi

Esta API permite interactuar con una base de datos de puntos de acceso WiFi, ofreciendo funcionalidades para obtener listas de puntos de acceso, buscar por ID, encontrar los más cercanos a una ubicación, y filtrar por alcaldía.

## Pasos para la Configuración e Instalación

1. **Clonar el Repositorio**

   Clona el repositorio utilizando el siguiente comando en tu terminal:
   ```bash
   git clone https://github.com/thekye2012/PuntosDeAccesoWifiCDMX.git
   ```
   Esto te permitirá obtener la última versión del código fuente del proyecto.

2. **Iniciar los Servicios con Docker Compose**

   Abre una terminal en la carpeta del proyecto clonado y ejecuta el siguiente comando para iniciar los servicios utilizando Docker Compose:
   ```bash
   docker-compose up -d
   ```
   Esto iniciará todos los servicios necesarios, incluidos los contenedores Docker para la aplicación y cualquier base de datos o dependencia externa configurada.

3. **Acceder a la Documentación Swagger**

   Una vez que los servicios estén en funcionamiento, puedes acceder a la documentación de Swagger para explorar los endpoints disponibles y probar la API. La documentación de Swagger suele estar disponible en:
   ```
   http://localhost:3000/swagger-ui/index.html
   ```

### Solución de Problemas

Si el servicio de "puntosdeacceso" no inicia correctamente, intenta ejecutar los siguientes comandos para limpiar y construir el proyecto antes de intentar nuevamente con Docker Compose:
```bash
mvn clean
mvn package
```
Después de ejecutar estos comandos, vuelve a intentar iniciar los servicios con:
```bash
docker-compose up
```

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


