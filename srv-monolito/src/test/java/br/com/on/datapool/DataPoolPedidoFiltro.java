package br.com.on.datapool;

import br.com.on.fiap.core.domain.model.PedidoFiltro;
import java.time.LocalDate;

public class DataPoolPedidoFiltro {

    public static PedidoFiltro gerarPedidoFiltro() {
        return new PedidoFiltro(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
