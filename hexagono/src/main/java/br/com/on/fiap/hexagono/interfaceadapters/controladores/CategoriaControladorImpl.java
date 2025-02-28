package br.com.on.fiap.hexagono.interfaceadapters.controladores;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.CategoriaControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.CategoriaApresentador;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.categoria.BuscaCategoriaCasoDeUso;
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
