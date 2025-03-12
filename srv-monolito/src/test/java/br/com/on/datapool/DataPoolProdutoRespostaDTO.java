package br.com.on.datapool;

import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.domain.Categoria;
import br.com.on.fiap.core.domain.Produto;
import java.math.BigDecimal;
import java.util.List;

public class DataPoolProdutoRespostaDTO {

    private static ProdutoResposta construirProdutoRespostaDTO(
            Long id, String nome, Categoria categoria, BigDecimal preco) {
        return ProdutoResposta.create(new Produto(id, nome, categoria, preco));
    }

    public static ProdutoResposta gerarProduto1() {
        return construirProdutoRespostaDTO(1L, "Produto 1", Categoria.LANCHE, BigDecimal.valueOf(10.0));
    }

    public static ProdutoResposta gerarProduto2() {
        return construirProdutoRespostaDTO(2L, "Produto 2", Categoria.ACOMPANHAMENTO, BigDecimal.valueOf(20.0));
    }

    public static ProdutoResposta gerarProduto3() {
        return construirProdutoRespostaDTO(3L, "Produto 3", Categoria.BEBIDA, BigDecimal.valueOf(30.0));
    }

    public static ProdutoResposta gerarProdutoXBurguer() {
        return construirProdutoRespostaDTO(1L, "x-burguer", Categoria.SOBREMESA, BigDecimal.TEN);
    }

    public static List<ProdutoResposta> gerarListaProdutoRespostaDTO() {
        return List.of(
                construirProdutoRespostaDTO(1L, "x-burguer", Categoria.LANCHE, BigDecimal.TEN),
                construirProdutoRespostaDTO(2L, "pizza", Categoria.LANCHE, BigDecimal.valueOf(20)));
    }
}
