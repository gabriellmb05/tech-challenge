package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.casosdeuso.produto.BuscaCategoriaCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.produto.BuscaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscaCategoriaCasoDeUsoTest {

	@Test
	@DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
	void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso = new BuscaCategoriaCasoDeUso();
        List<Categoria> categorias = buscaCategoriaCasoDeUso.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
	}
}
