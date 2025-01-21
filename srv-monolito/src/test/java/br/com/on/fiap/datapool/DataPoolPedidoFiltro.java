package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import java.time.LocalDate;

public class DataPoolPedidoFiltro {

    public static PedidoFiltro gerarPedidoFiltro() {
        return new PedidoFiltro(LocalDate.now(), LocalDate.now(), 1L, "45216784918");
    }
}
