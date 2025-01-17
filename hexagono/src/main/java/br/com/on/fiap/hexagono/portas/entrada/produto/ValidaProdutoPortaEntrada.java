package br.com.on.fiap.hexagono.portas.entrada.produto;

import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import java.util.List;

public interface ValidaProdutoPortaEntrada {
	void validar(List<RelPedidoProduto> produtos);
}
