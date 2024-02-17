package com.promotores.puntosdeaccesowificdmx.service;

import org.springframework.data.geo.Point;
import com.promotores.puntosdeaccesowificdmx.entities.Wifi;
import com.promotores.puntosdeaccesowificdmx.repository.WifiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WifiService {

    private final WifiRepository wifiRepository;

    public WifiService(WifiRepository wifiRepository) {
        this.wifiRepository = wifiRepository;
    }

    /**
     * Obtiene una lista paginada de todos los puntos de acceso WiFi.
     *
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi
     */
    public Page<Wifi> findAll(Pageable pageable) {
        return wifiRepository.findAll(pageable);
    }

    /**
     * Obtiene un punto de acceso WiFi por su ID.
     *
     * @param id Identificador único del punto de acceso WiFi
     * @return Objeto Wifi con la información del punto de acceso
     * @throws ResponseStatusException Si no se encuentra el punto de acceso
     */
    public Wifi findById(String id) {
        Wifi wifi = wifiRepository.findByWifiId(id);
        if (wifi == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto de acceso WiFi no encontrado");
        }
        return wifi;
    }

    /**
     * Filtra y obtiene una lista paginada de puntos de acceso WiFi por alcaldía.
     *
     * @param alcaldia Alcaldía a la que se desea filtrar
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi filtrados por alcaldía
     */
    public Page<Wifi> findByAlcaldia(String alcaldia, Pageable pageable) {
        return wifiRepository.findByAlcaldia(alcaldia, pageable);
    }

    /**
     * Obtiene los puntos de acceso WiFi más cercanos a una coordenada dada.
     *
     * @param point Coordenada con latitud y longitud
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi ordenados por distancia a la coordenada
     */
    public Page<Wifi> findNearest(Point point, Pageable pageable) {
        return wifiRepository.findByUbicacionNear(point, pageable);
    }

}
