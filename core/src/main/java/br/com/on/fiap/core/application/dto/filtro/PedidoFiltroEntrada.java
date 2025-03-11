package br.com.on.fiap.core.application.dto.filtro;

import java.time.LocalDate;

public interface PedidoFiltroEntrada {

    LocalDate getDataInicio();

    LocalDate getDataFim();

    Long getSituacao();

    String getCpfCliente();

    static PedidoFiltroEntrada create(LocalDate dataInicio, LocalDate dataFim, Long situacao, String cpfCliente) {
        return new PedidoFiltroEntrada() {
            @Override
            public LocalDate getDataInicio() {
                return dataInicio;
            }

            @Override
            public LocalDate getDataFim() {
                return dataFim;
            }

            @Override
            public Long getSituacao() {
                return situacao;
            }

            @Override
            public String getCpfCliente() {
                return cpfCliente;
            }
        };
    }
}
