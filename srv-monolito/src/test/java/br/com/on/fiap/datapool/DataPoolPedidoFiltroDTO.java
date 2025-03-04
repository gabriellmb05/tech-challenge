package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.pedido.entrada.dto.filtro.PedidoFiltroDTO;
import java.time.LocalDate;

public class DataPoolPedidoFiltroDTO {

    public static PedidoFiltroDTO gerarPedido1() {
        return new PedidoFiltroDTO(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
