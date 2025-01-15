package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaCategoriaPortaEntrada;

import java.util.List;

public class BuscaCategoriaCasoDeUso implements BuscaCategoriaPortaEntrada {

	@Override
	public List<Categoria> buscaCategorias() {
		return List.of(Categoria.values());
	}
}
