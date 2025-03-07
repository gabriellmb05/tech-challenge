package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.domain.Pedido;

public interface PedidoListaUseCase {

    Pagina<Pedido> buscarPedidosComFiltro(PedidoFiltroEntrada filtro, Paginacao paginacao);
}
