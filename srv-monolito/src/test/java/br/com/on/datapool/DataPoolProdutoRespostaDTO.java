package br.com.on.datapool;

import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import java.math.BigDecimal;
import java.util.List;

public class DataPoolProdutoRespostaDTO {

    private static ProdutoResposta construirProdutoRespostaDTO(
            Long id, String nome, String categoria, BigDecimal preco) {
        return ProdutoResponse.builder()
                .id(id)
                .nome(nome)
                .categoria(categoria)
                .preco(preco)
                .build();
    }

    public static ProdutoResposta gerarProduto1() {
        return construirProdutoRespostaDTO(1L, "Produto 1", "Descricao 1", BigDecimal.valueOf(10.0));
    }

    public static ProdutoResposta gerarProduto2() {
        return construirProdutoRespostaDTO(2L, "Produto 2", "Descricao 2", BigDecimal.valueOf(20.0));
    }

    public static ProdutoResposta gerarProduto3() {
        return construirProdutoRespostaDTO(3L, "Produto 3", "Descricao 3", BigDecimal.valueOf(30.0));
    }

    public static ProdutoResposta gerarProdutoXBurguer() {
        return construirProdutoRespostaDTO(1L, "x-burguer", "LANCHE", BigDecimal.TEN);
    }

    public static List<ProdutoResposta> gerarListaProdutoRespostaDTO() {
        return List.of(
                construirProdutoRespostaDTO(1L, "x-burguer", "LANCHE", BigDecimal.TEN),
                construirProdutoRespostaDTO(2L, "pizza", "LANCHE", BigDecimal.valueOf(20)));
    }
}
