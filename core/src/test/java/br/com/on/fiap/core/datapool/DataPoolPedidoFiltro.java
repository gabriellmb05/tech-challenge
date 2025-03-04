package br.com.on.fiap.core.datapool;

import br.com.on.fiap.core.domain.entity.PedidoFiltro;
import java.time.LocalDate;

public class DataPoolPedidoFiltro {

    private DataPoolPedidoFiltro() {}

    public static PedidoFiltro pedidoFiltroComPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return new PedidoFiltro(dataInicio, dataFim, null, null);
    }

    public static PedidoFiltro pedidoFiltroComSituacao(Long situacao) {
        return new PedidoFiltro(null, null, situacao, null);
    }

    public static PedidoFiltro pedidoFiltroComCpfCliente(String cpfCliente) {
        return new PedidoFiltro(null, null, null, cpfCliente);
    }

    public static PedidoFiltro pedidoFiltroVazio() {
        return new PedidoFiltro();
    }
}
