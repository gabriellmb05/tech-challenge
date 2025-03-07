package br.com.on.fiap.core.application.dto.filtro;

import java.time.LocalDate;

public interface PedidoFiltroEntrada {

    LocalDate getDataInicio();

    LocalDate getDataFim();

    Long getSituacao();

    String getCpfCliente();
}
