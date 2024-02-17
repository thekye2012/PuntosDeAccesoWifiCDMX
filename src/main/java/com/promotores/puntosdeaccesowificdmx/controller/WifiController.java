package com.promotores.puntosdeaccesowificdmx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;
import com.promotores.puntosdeaccesowificdmx.entities.Wifi;
import com.promotores.puntosdeaccesowificdmx.service.WifiService;

@RestController
@RequestMapping("/api/wifi")
@Tag(name = "Puntos de acceso WiFi")
public class WifiController {

    private final WifiService wifiService;

    public WifiController(WifiService wifiService) {
        this.wifiService = wifiService;
    }

    /**
     * Obtiene una lista paginada de todos los puntos de acceso WiFi.
     *
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi
     */
    @GetMapping
    @Operation(summary = "Obtiene una lista paginada de todos los puntos de acceso WiFi", description = "Obtiene una lista paginada de todos los puntos de acceso WiFi", tags = {"Puntos de acceso WiFi"})
    public Page<Wifi> getAll(Pageable pageable) {
        return wifiService.findAll(pageable);
    }

    /**
     * Obtiene un punto de acceso WiFi por su ID.
     *
     * @param id Identificador único del punto de acceso WiFi
     * @return Objeto Wifi con la información del punto de acceso
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un punto de acceso WiFi por su ID", description = "Obtiene un punto de acceso WiFi por su ID", tags = {"Puntos de acceso WiFi"})
    public Wifi getById(@PathVariable String id) {
        return wifiService.findById(id);
    }

    /**
     * Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía.
     *
     * @param alcaldia Alcaldía a la que se desea filtrar
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi filtrados por alcaldía
     */
    @GetMapping("/by-alcaldia/{alcaldia}")
    @Operation(summary = "Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía", description = "Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía", tags = {"Puntos de acceso WiFi"})
    public Page<Wifi> getByAlcaldia(@PathVariable String alcaldia, Pageable pageable) {
        return wifiService.findByAlcaldia(alcaldia, pageable);
    }

    /**
     * Obtiene los puntos de acceso WiFi más cercanos a una coordenada dada.
     *
     * @param latitude Latitud de la coordenada
     * @param longitude Longitud de la coordenada
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi ordenados por distancia a la coordenada
     */
    @GetMapping("/nearest")
    @Operation(summary = "Obtiene los puntos de acceso WiFi más cercanos a una coordenada dada", description = "Obtiene los puntos de acceso WiFi más cercanos a una coordenada dada", tags = {"Puntos de acceso WiFi"})
    public Page<Wifi> findNearest(@RequestParam double latitude, @RequestParam double longitude, Pageable pageable) {
        Point point = new Point(longitude, latitude); // ¡OJO! Orden: longitud, latitud
        return wifiService.findNearest(point, pageable);
    }

}
