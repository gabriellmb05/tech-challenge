package br.com.on.fiap.core.application.usecase.pedido;

import br.com.on.fiap.core.application.dto.entrada.ProdutoQuantidadeSolicitacao;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.Map;

public interface PedidoValidaProdutoUseCase {

    Map<Produto, Long> validarProdutos(List<ProdutoQuantidadeSolicitacao> produtosSolicitados);
}
