package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.produto.entrada.dto.filtro.ProdutoFiltroDTO;

public class DataPoolProdutoFiltroDTO {

    public static ProdutoFiltroDTO gerarProdutoXBurguer() {
        return new ProdutoFiltroDTO("x-burguer", "LANCHE");
    }
}
