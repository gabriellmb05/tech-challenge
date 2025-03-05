package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoRequest;
import java.math.BigDecimal;
import net.datafaker.Faker;

public class DataPoolProdutoSolicitacaoDTO {

    private static final Faker faker = new Faker();

    private static ProdutoSolicitacaoRequest construirProduto(String nome, String categoria, BigDecimal preco) {
        return ProdutoSolicitacaoRequest.builder()
                .nome(nome)
                .categoria(categoria)
                .preco(preco)
                .build();
    }

    public static ProdutoSolicitacaoRequest gerarComNome(String nome) {
        return construirProduto(
                nome, faker.book().title(), BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
    }

    public static ProdutoSolicitacaoRequest gerarComCategoria(String categoria) {
        return construirProduto(
                faker.name().firstName(),
                categoria,
                BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
    }

    public static ProdutoSolicitacaoRequest gerarComPreco(BigDecimal preco) {
        return construirProduto(faker.name().firstName(), faker.book().title(), preco);
    }

    public static ProdutoSolicitacaoRequest gerarNomeCategoriaPreco(String nome, String categoria, BigDecimal preco) {
        return construirProduto(nome, categoria, preco);
    }

    public static ProdutoSolicitacaoRequest gerarProduto1() {
        return construirProduto("Produto 1", "Descricao 1", BigDecimal.valueOf(10.0));
    }

    public static ProdutoSolicitacaoRequest gerarProduto2() {
        return construirProduto("Produto 2", "Descricao 2", BigDecimal.valueOf(20.0));
    }

    public static ProdutoSolicitacaoRequest gerarProduto3() {
        return construirProduto("Produto 3", "Descricao 3", BigDecimal.valueOf(30.0));
    }
}
