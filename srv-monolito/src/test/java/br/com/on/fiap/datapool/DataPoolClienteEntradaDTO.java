package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import java.time.LocalDate;

public class DataPoolClienteEntradaDTO {

    public static ClienteEntradaDTO gerarCliente() {
        return new ClienteEntradaDTO("43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
