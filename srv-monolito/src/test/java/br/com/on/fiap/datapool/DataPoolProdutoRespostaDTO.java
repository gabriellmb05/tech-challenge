package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ProdutoRespostaDTO;
import java.math.BigDecimal;
import java.util.List;

public class DataPoolProdutoRespostaDTO {

	private static ProdutoRespostaDTO construirProdutoRespostaDTO(Long id, String nome, String categoria,
			BigDecimal preco) {
		return ProdutoRespostaDTO.builder().id(id).nome(nome).categoria(categoria).preco(preco).build();
	}

	public static ProdutoRespostaDTO gerarProduto1() {
		return construirProdutoRespostaDTO(1L, "Produto 1", "Descricao 1", BigDecimal.valueOf(10.0));
	}

	public static ProdutoRespostaDTO gerarProduto2() {
		return construirProdutoRespostaDTO(2L, "Produto 2", "Descricao 2", BigDecimal.valueOf(20.0));
	}

	public static ProdutoRespostaDTO gerarProduto3() {
		return construirProdutoRespostaDTO(3L, "Produto 3", "Descricao 3", BigDecimal.valueOf(30.0));
	}

	public static ProdutoRespostaDTO gerarProdutoXBurguer() {
		return construirProdutoRespostaDTO(1L, "x-burguer", "LANCHE", BigDecimal.TEN);
	}

	public static List<ProdutoRespostaDTO> gerarListaProdutoRespostaDTO() {
		return List.of(construirProdutoRespostaDTO(1L, "x-burguer", "LANCHE", BigDecimal.TEN),
				construirProdutoRespostaDTO(2L, "pizza", "LANCHE", BigDecimal.valueOf(20)));
	}
}
