package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import java.time.LocalDate;

public class PedidoFiltroEntradaDataPool {

    private PedidoFiltroEntradaDataPool() {}

    public static PedidoFiltroEntrada criarFiltroComPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return PedidoFiltroEntrada.create(dataInicio, dataFim, null, null);
    }

    public static PedidoFiltroEntrada criarFiltroComSituacao(Long situacao) {
        return PedidoFiltroEntrada.create(null, null, situacao, null);
    }

    public static PedidoFiltroEntrada criarFiltroComCpfCliente(String cpfCliente) {
        return PedidoFiltroEntrada.create(null, null, null, cpfCliente);
    }

    public static PedidoFiltroEntrada criarFiltroVazio() {
        return PedidoFiltroEntrada.create(null, null, null, null);
    }
}
