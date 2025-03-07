package br.com.on.fiap.core.domain.model;

import java.time.LocalDate;

public interface PedidoFiltroEntrada {

    LocalDate getDataInicio();

    LocalDate getDataFim();

    Long getSituacao();

    String getCpfCliente();
}
