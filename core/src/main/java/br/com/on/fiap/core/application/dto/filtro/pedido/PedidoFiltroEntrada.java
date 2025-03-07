package br.com.on.fiap.core.application.dto.filtro.pedido;

import java.time.LocalDate;

public interface PedidoFiltroEntrada {

    LocalDate getDataInicio();

    LocalDate getDataFim();

    Long getSituacao();

    String getCpfCliente();
}
