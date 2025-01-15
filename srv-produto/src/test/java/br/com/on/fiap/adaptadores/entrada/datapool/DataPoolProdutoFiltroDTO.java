package br.com.on.fiap.adaptadores.entrada.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoFiltroDTO;

public class DataPoolProdutoFiltroDTO {

	public static ProdutoFiltroDTO gerarProdutoXBurguer() {
		return new ProdutoFiltroDTO("x-burguer", "LANCHE");
	}

}
