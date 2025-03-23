package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoListaUseCase {

    PaginaResposta<Pedido> buscarPedidosComFiltro(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    PaginaResposta<Pedido> buscarPedidosComFiltro(PaginacaoResposta paginacaoResposta);
}
