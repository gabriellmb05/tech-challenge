package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.filter.PedidoFiltroDTO;
import java.time.LocalDate;

public class DataPoolPedidoFiltroDTO {

    public static PedidoFiltroDTO gerarPedido1() {
        return new PedidoFiltroDTO(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
