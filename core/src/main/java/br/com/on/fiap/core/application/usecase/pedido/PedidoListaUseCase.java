package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.filtro.pedido.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoListaUseCase {

    PaginaResposta<Pedido> buscarPedidosComFiltro(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);
}
