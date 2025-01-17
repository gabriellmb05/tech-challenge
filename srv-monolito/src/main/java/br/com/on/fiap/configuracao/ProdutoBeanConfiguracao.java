package br.com.on.fiap.configuracao;

import br.com.on.fiap.adaptadores.saida.servico.PersistenciaProdutoAdaptador;
import br.com.on.fiap.hexagono.casosdeuso.produto.*;
import br.com.on.fiap.hexagono.portas.entrada.produto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

	@Bean
	public BuscaProdutoPorIdPortaEntrada buscaProduto(PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
		return new BuscaProdutoCasoDeUso(persistenciaProdutoAdaptador);
	}

	@Bean
	public InsereProdutoPortaEntrada insereProduto(PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
		return new InsereProdutoCasoDeUso(persistenciaProdutoAdaptador);
	}

	@Bean
	public AlteraProdutoPortaEntrada alteraProduto(PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
		return new AlteraProdutoCasoDeUso(persistenciaProdutoAdaptador);
	}

	@Bean
	public DeletaProdutoPortaEntrada deletaProduto(PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
		return new DeletaProdutoCasoDeUso(persistenciaProdutoAdaptador);
	}

	@Bean
	public ListarProdutoPortaEntrada listarProduto(PersistenciaProdutoAdaptador persistenciaProdutoAdapter) {
		return new ListarProdutoCasoDeUso(persistenciaProdutoAdapter);
	}

	@Bean
	public BuscaCategoriaPortaEntrada buscaCategorias() {
		return new BuscaCategoriaCasoDeUso();
	}

	@Bean
	public ValidaProdutosCasoDeUso validaProdutosCasoDeUso(PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
		return new ValidaProdutosCasoDeUso(persistenciaProdutoAdaptador);
	}
}
