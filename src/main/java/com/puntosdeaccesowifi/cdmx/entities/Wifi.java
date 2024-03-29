package com.puntosdeaccesowifi.cdmx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "wifi") // La colección en MongoDB será 'wifi'
@Getter // Anotaciones Lombok para generar getters
@Setter // Anotaciones Lombok para generar setters
@NoArgsConstructor // Anotaciones Lombok para generar constructor sin argumentos
@AllArgsConstructor // Anotaciones Lombok para generar constructor con todos los argumentos
public class Wifi {

    @Id
    @JsonIgnore // Indica que '_id' se excluirá en la serialización a JSON
    @Field("_id") // Nombre del campo en MongoDB
    private String idMongodb; // ID de MongoDB

    private String id; // ID del punto de acceso WiFi
    private String programa;
    private LocalDate fechaInstalacion;
    private Double latitud;
    private Double longitud;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE) // Optimiza búsquedas geoespaciales
    @JsonIgnore // Indica que 'ubicacion' se excluirá en la serialización a JSON
    private List<Double> ubicacion; // Almacena coordenadas (se podría usar la clase Point)

    private String colonia;
    private String alcaldia;
}
