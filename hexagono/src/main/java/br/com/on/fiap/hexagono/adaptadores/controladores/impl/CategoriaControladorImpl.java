package br.com.on.fiap.hexagono.adaptadores.controladores.impl;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.CategoriaApresentador;
import br.com.on.fiap.hexagono.adaptadores.controladores.CategoriaControlador;
import br.com.on.fiap.hexagono.adaptadores.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.casodeuso.categoria.entrada.BuscaCategoriaCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Categoria;
import java.util.List;

public class CategoriaControladorImpl implements CategoriaControlador {

    private final BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso;
    private final CategoriaApresentador categoriaApresentador;

    public CategoriaControladorImpl(
            BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso, CategoriaApresentador categoriaApresentador) {
        this.buscaCategoriaCasoDeUso = buscaCategoriaCasoDeUso;
        this.categoriaApresentador = categoriaApresentador;
    }

    public CategoriaSaidaDTO buscaCategorias() {
        List<Categoria> categorias = buscaCategoriaCasoDeUso.buscaCategorias();
        return categoriaApresentador.formatar(categorias);
    }
}
