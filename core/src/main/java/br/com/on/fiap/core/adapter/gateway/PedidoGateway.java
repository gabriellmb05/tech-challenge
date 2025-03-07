package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.domain.Pedido;
import java.util.Optional;

public interface PedidoGateway {

    Optional<Pedido> atualizarPedido(String protocolo);

    Pagina<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, Paginacao paginacao);

    Optional<Pedido> detalhaPedido(String protocolo);

    Pedido salvaPedido(Pedido pedido);
}
