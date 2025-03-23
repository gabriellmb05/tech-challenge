package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;
import java.util.Optional;

public interface PedidoDataSource {
    Optional<Pedido> atualizarPedido(String protocolo);

    PaginaResposta<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    PaginaResposta<Pedido> listarComFiltros(PaginacaoResposta paginacaoResposta);

    Optional<Pedido> detalhaPedido(String protocolo);

    Pedido salvaPedido(Pedido pedido);
}
