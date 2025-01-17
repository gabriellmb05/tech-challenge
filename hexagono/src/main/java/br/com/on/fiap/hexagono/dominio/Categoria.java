package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.stream.Stream;

public enum Categoria {
    LANCHE("LANCHE"),
    ACOMPANHAMENTO("ACOMPANHAMENTO"),
    BEBIDA("BEBIDA"),
    SOBREMESA("SOBREMESA");

    private final String nome;

    Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public static Categoria buscaCategoria(String categoria) {
        return Stream.of(Categoria.values())
                .filter(c -> c.nome.equals(categoria))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_CATEGORIA_NAO_CADASTRADA.getMensagem(), categoria));
    }
}
