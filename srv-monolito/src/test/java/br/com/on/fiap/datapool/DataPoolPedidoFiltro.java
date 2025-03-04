package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.entity.PedidoFiltro;

import java.time.LocalDate;

public class DataPoolPedidoFiltro {

    public static PedidoFiltro gerarPedidoFiltro() {
        return new PedidoFiltro(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
