package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.entrada.ProdutoRequest;
import java.math.BigDecimal;
import net.datafaker.Faker;

public class DataPoolProdutoSolicitacaoDTO {

    private static final Faker faker = new Faker();

    private static ProdutoRequest construirProduto(String nome, String categoria, BigDecimal preco) {
        return ProdutoRequest.builder()
                .nome(nome)
                .categoria(categoria)
                .preco(preco)
                .build();
    }

    public static ProdutoRequest gerarComNome(String nome) {
        return construirProduto(
                nome, faker.book().title(), BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
    }

    public static ProdutoRequest gerarComCategoria(String categoria) {
        return construirProduto(
                faker.name().firstName(),
                categoria,
                BigDecimal.valueOf(faker.number().numberBetween(1.0, 100.0)));
    }

    public static ProdutoRequest gerarComPreco(BigDecimal preco) {
        return construirProduto(faker.name().firstName(), faker.book().title(), preco);
    }

    public static ProdutoRequest gerarNomeCategoriaPreco(String nome, String categoria, BigDecimal preco) {
        return construirProduto(nome, categoria, preco);
    }

    public static ProdutoRequest gerarProduto1() {
        return construirProduto("Produto 1", "Descricao 1", BigDecimal.valueOf(10.0));
    }

    public static ProdutoRequest gerarProduto2() {
        return construirProduto("Produto 2", "Descricao 2", BigDecimal.valueOf(20.0));
    }

    public static ProdutoRequest gerarProduto3() {
        return construirProduto("Produto 3", "Descricao 3", BigDecimal.valueOf(30.0));
    }
}
