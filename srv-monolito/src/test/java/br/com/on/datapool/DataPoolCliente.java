package br.com.on.datapool;

import br.com.on.fiap.core.domain.Cliente;
import java.time.LocalDate;

public class DataPoolCliente {

    public static Cliente gerarCliente() {
        return new Cliente(1L, "43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
