package com.puntosdeaccesowifi.cdmx.controller;

import com.puntosdeaccesowifi.cdmx.entities.Wifi;
import com.puntosdeaccesowifi.cdmx.service.WifiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GraphqlControllerTest {

    @Mock
    private WifiService wifiService;

    @InjectMocks
    private GraphqlController graphqlController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllWifi_ReturnsPage() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiService.findAll(any(PageRequest.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = graphqlController.getAllWifi(0, 2);

        // Verificación
        verify(wifiService).findAll(any(PageRequest.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
    }

    @Test
    void getWifiById_ReturnsWifi() {
        // Preparación
        Wifi expectedWifi = new Wifi(); // Configura el objeto Wifi según sea necesario
        when(wifiService.findById(any(String.class))).thenReturn(expectedWifi);

        // Acción
        Wifi actualWifi = graphqlController.getWifiById("1");

        // Verificación
        verify(wifiService).findById("1");
        assertNotNull(actualWifi);
        assertEquals(expectedWifi, actualWifi);
    }

    @Test
    void getWifiByAlcaldia_ReturnsPage() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiService.findByAlcaldia(eq("Cuauhtémoc"), any(PageRequest.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = graphqlController.getWifiByAlcaldia("Cuauhtémoc", 0, 2);

        // Verificación
        verify(wifiService).findByAlcaldia(eq("Cuauhtémoc"), any(PageRequest.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
    }

    @Test
    void getWifiByUbicacion_ReturnsPage() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiService.findNearest(any(Point.class), any(PageRequest.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = graphqlController.getWifiByUbicacion(19.4326, -99.1332, 0, 2);

        // Verificación
        verify(wifiService).findNearest(any(Point.class), any(PageRequest.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements());
    }
}
