package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.application.dto.filtro.pedido.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;
import java.util.Optional;

public interface PedidoDataSource {
    Optional<Pedido> atualizarPedido(String protocolo);

    PaginaResposta<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    Optional<Pedido> detalhaPedido(String protocolo);

    Pedido salvaPedido(Pedido pedido);
}
