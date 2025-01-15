package br.com.on.fiap.adaptadores.entrada.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import net.datafaker.Faker;
import java.math.BigDecimal;

public class DataPoolProdutoSolicitacaoDTO {

	private static final Faker faker = new Faker();

	private static ProdutoSolicitacaoDTO construirProduto(String nome, String categoria, BigDecimal preco) {
		return ProdutoSolicitacaoDTO.builder().nome(nome).categoria(categoria).preco(preco).build();
	}

	public static ProdutoSolicitacaoDTO gerarComNome(String nome) {
		return construirProduto(nome, faker.book().title(),
				BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
	}

	public static ProdutoSolicitacaoDTO gerarComCategoria(String categoria) {
		return construirProduto(faker.name().firstName(), categoria,
				BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
	}

	public static ProdutoSolicitacaoDTO gerarComPreco(BigDecimal preco) {
		return construirProduto(faker.name().firstName(), faker.book().title(), preco);
	}

	public static ProdutoSolicitacaoDTO gerarNomeCategoriaPreco(String nome, String categoria, BigDecimal preco) {
		return construirProduto(nome, categoria, preco);
	}

	public static ProdutoSolicitacaoDTO gerarProduto1() {
		return construirProduto("Produto 1", "Descricao 1", BigDecimal.valueOf(10.0));
	}

	public static ProdutoSolicitacaoDTO gerarProduto2() {
		return construirProduto("Produto 2", "Descricao 2", BigDecimal.valueOf(20.0));
	}

	public static ProdutoSolicitacaoDTO gerarProduto3() {
		return construirProduto("Produto 3", "Descricao 3", BigDecimal.valueOf(30.0));
	}
}
