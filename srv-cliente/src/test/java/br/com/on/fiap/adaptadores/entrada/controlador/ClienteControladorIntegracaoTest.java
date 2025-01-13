package br.com.on.fiap.adaptadores.entrada.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ClienteControladorIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBuscaClientePorCpf() throws Exception {
        mockMvc.perform(get("/clientes/{cpf}", "43316652616"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("43316652616"));
    }
}