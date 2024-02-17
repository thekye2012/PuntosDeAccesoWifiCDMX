package com.puntosdeaccesowifi.cdmx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PuntosDeAccesoWifiCdmxApplicationTests {
    @Autowired
    private PuntosDeAccesoWifiCdmxApplication application;
    @Test
    void contextLoads() {
        assertThat(application).isNotNull();
    }

}
