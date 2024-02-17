package com.promotores.puntosdeaccesowificdmx.repository;

import org.springframework.data.geo.Point;
import com.promotores.puntosdeaccesowificdmx.entities.Wifi;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

@Repository
public interface WifiRepository extends MongoRepository<Wifi, String> {

    /**
     * Obtiene una lista paginada de puntos de acceso WiFi cercanos a una coordenada.
     *
     * @param point Coordenada con latitud y longitud
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi ordenados por distancia a la coordenada
     */
    Page<Wifi> findByUbicacionNear(Point point, Pageable pageable);

    /**
     * Obtiene una lista paginada de puntos de acceso WiFi por alcaldía.
     *
     * @param alcaldia Alcaldía a la que se desea filtrar
     * @param pageable Parámetros de paginación (página actual, tamaño de página, etc.)
     * @return Página con la lista de puntos WiFi filtrados por alcaldía
     */
    Page<Wifi> findByAlcaldia(String alcaldia, Pageable pageable);

    /**
     * Obtiene un punto de acceso WiFi por su ID.
     *
     * @param id Identificador único del punto de acceso WiFi
     * @return Objeto Wifi con la información del punto de acceso
     */
    @Query("{ 'id' : ?0 }")
    Wifi findByWifiId(String id);

}
