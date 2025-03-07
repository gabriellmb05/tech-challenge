package br.com.on.datapool;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import java.time.LocalDate;

public class DataPoolClienteEntradaDTO {

    public static ClienteEntrada gerarCliente() {
        return new ClienteEntrada("43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
