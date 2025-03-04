package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import java.time.LocalDate;

public class DataPoolClienteEntradaDTO {

    public static ClienteEntradaDTO gerarCliente() {
        return new ClienteEntradaDTO("43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
