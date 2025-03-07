package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;
import java.util.Optional;

public interface PedidoDataSource {
    Optional<Pedido> atualizarPedido(String protocolo);

    Pagina<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    Optional<Pedido> detalhaPedido(String protocolo);

    Pedido salvaPedido(Pedido pedido);
}
