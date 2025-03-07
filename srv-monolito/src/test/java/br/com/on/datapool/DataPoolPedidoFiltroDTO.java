package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filter.PedidoFiltroRequest;
import java.time.LocalDate;

public class DataPoolPedidoFiltroDTO {

    public static PedidoFiltroRequest gerarPedido1() {
        return new PedidoFiltroRequest(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
