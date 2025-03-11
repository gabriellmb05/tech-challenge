package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import java.time.LocalDate;

public class ClienteEntradaDataPool {

    private ClienteEntradaDataPool() {}

    public static ClienteEntrada criarClienteComCpf(String cpf) {
        return ClienteEntrada.create(
                "Maria Oliveira",
                cpf,
                "maria@exemplo.com",
                LocalDate.of(1990, 5, 20)
        );
    }

    public static ClienteEntrada criarClienteValido() {
        return ClienteEntrada.create(
                "Carlos Souza",
                "12345678900",
                "carlos.souza@example.com",
                LocalDate.of(1985, 7, 15)
        );
    }
}
