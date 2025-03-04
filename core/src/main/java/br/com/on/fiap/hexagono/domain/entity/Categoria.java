package br.com.on.fiap.hexagono.domain.entity;

import br.com.on.fiap.hexagono.domain.exception.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import java.util.Objects;
import java.util.stream.Stream;

public enum Categoria {
    LANCHE(1),
    ACOMPANHAMENTO(2),
    BEBIDA(3),
    SOBREMESA(4);

    private final Integer codigo;

    Categoria(Integer codigo) {
        this.codigo = codigo;
    }

    public static Categoria deCodigo(Integer codigo) {
        return Stream.of(Categoria.values())
                .filter(categoria -> Objects.equals(categoria.getCodigo(), codigo))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_PRODUTO_CATEGORIA_NAO_CADASTRADA.getMensagem(), codigo));
    }

    public static Categoria deString(String categoriaStr) {
        return Stream.of(Categoria.values())
                .filter(categoria -> categoria.name().equalsIgnoreCase(categoriaStr))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_PRODUTO_CATEGORIA_NAO_CADASTRADA.getMensagem(), categoriaStr));
    }

    public Integer getCodigo() {
        return codigo;
    }
}
