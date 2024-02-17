package com.puntosdeaccesowifi.cdmx.controller;

import com.puntosdeaccesowifi.cdmx.entities.Wifi;
import com.puntosdeaccesowifi.cdmx.service.WifiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphqlController {
    private final WifiService wifiService;

    public GraphqlController(WifiService wifiService) {
        this.wifiService = wifiService;
    }
    /**
     * Obtiene todos los puntos Wifi con paginación.
     *
     * @param page Página actual (índice comienza en 0)
     * @param size Tamaño de la página
     * @return Página con la lista de todos los puntos WiFi
     */
    @QueryMapping
    public Page<Wifi> getAllWifi(@Argument("page") int page, @Argument("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return wifiService.findAll(pageable);
    }
    /**
     * Obtiene un punto Wifi por su ID.
     *
     * @param id Identificador único del punto Wifi
     * @return Punto Wifi con el ID especificado
     */
    @QueryMapping
    public Wifi getWifiById(@Argument("id") String id) {
        return wifiService.findById(id);
    }
    /**
     * Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía.
     *
     * @param alcaldia Alcaldía a la que se desea filtrar
     * @param page Página actual (índice comienza en 0)
     * @param size Tamaño de la página
     * @return Página con la lista de puntos WiFi filtrados por alcaldía
     */
    @QueryMapping
    public Page<Wifi> getWifiByAlcaldia(@Argument("alcaldia") String alcaldia, @Argument("page") int page, @Argument("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return wifiService.findByAlcaldia(alcaldia, pageable);
    }
    /**
     * Obtiene los puntos Wifi más cercanos a un punto geográfico (longitud y latitud).
     *
     * @param latitud Latitud del punto geográfico
     * @param longitud Longitud del punto geográfico
     * @param page Página actual (índice comienza en 0)
     * @param size Tamaño de la página
     * @return Página con la lista de puntos WiFi más cercanos al punto geográfico
     */
    @QueryMapping
    public Page<Wifi> getWifiByUbicacion(@Argument("latitud") Double latitud, @Argument("longitud") Double longitud, @Argument("page") int page, @Argument("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Point point = new Point(longitud, latitud); // Asegúrate de que el orden es correcto según tu implementación
        return wifiService.findNearest(point, pageable);
    }
}
