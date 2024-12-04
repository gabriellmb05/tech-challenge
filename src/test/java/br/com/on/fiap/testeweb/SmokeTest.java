package br.com.on.fiap.testeweb;

import br.com.on.fiap.aplicacao.adaptadores.controladores.ClientesController;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private ClientesController controller;

    //@Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}