package com.puntosdeaccesowifi.cdmx.controller;

import com.puntosdeaccesowifi.cdmx.entities.Wifi;
import com.puntosdeaccesowifi.cdmx.repository.WifiRepository;
import com.puntosdeaccesowifi.cdmx.service.WifiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WifiControllerTest {
    @Mock
    private WifiRepository wifiRepository;
    private WifiController wifiController;



    @BeforeEach
    void setUp() {
        WifiService wifiService = new WifiService(wifiRepository);
        wifiController = new WifiController(wifiService);

    }

    @Test
    void getAll() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = wifiController.getAll(Pageable.unpaged());

        // Verificación
        verify(wifiRepository).findAll(any(Pageable.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements(), "The expected and actual page sizes should match");
        assertEquals(expectedPage.getContent().size(), actualPage.getContent().size(), "The number of Wifis returned was not as expected");
    }

    @Test
    void getById() {
        // Preparación
        Wifi expectedWifi = new Wifi();
        when(wifiRepository.findByWifiId(any(String.class))).thenReturn(expectedWifi);

        // Acción
        Wifi actualWifi = wifiController.getById("1");

        // Verificación
        verify(wifiRepository).findByWifiId(any(String.class));
        assertNotNull(actualWifi);
        assertEquals(expectedWifi, actualWifi, "The expected and actual Wifi should match");

    }

    @Test
    void getByAlcaldia() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiRepository.findByAlcaldia(any(String.class), any(Pageable.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = wifiController.getByAlcaldia("Cuauhtémoc", Pageable.unpaged());

        // Verificación
        verify(wifiRepository).findByAlcaldia(any(String.class), any(Pageable.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements(), "The expected and actual page sizes should match");
        assertEquals(expectedPage.getContent().size(), actualPage.getContent().size(), "The number of Wifis returned was not as expected");
    }

    @Test
    void findNearest() {
        // Preparación
        Page<Wifi> expectedPage = new PageImpl<>(Arrays.asList(new Wifi(), new Wifi()));
        when(wifiRepository.findByUbicacionNear(any(), any(Pageable.class))).thenReturn(expectedPage);

        // Acción
        Page<Wifi> actualPage = wifiController.findNearest(19.4326, 99.1332, Pageable.unpaged());

        // Verificación
        verify(wifiRepository).findByUbicacionNear(any(), any(Pageable.class));
        assertNotNull(actualPage);
        assertEquals(expectedPage.getTotalElements(), actualPage.getTotalElements(), "The expected and actual page sizes should match");
        assertEquals(expectedPage.getContent().size(), actualPage.getContent().size(), "The number of Wifis returned was not as expected");
    }
}