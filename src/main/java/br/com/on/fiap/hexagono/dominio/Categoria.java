package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.adaptadores.entrada.controlador.excecao.EntradaDadosInvalidaExcecao;

public enum Categoria {
    Lanche, Acompanhamento, Bebida, Sobremesa;

    public static Categoria buscaCategoria(String categoria){
        return Categoria.valueOf(categoria);
    }
}