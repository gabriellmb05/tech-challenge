package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.springframework.data.domain.Page;

public class PaginacaoMapeador {

	public static Page<ProdutoRespostaDTO> paraPageProdutoRespostaDTO(Page<Produto> paginacao,
			ProdutoEntradaMapeador produtoEntradaMapeador) {
		return paginacao.map(produtoEntradaMapeador::paraProdutoDTO);
	}
}
