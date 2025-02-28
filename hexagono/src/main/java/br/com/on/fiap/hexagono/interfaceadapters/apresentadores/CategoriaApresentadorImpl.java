package br.com.on.fiap.hexagono.interfaceadapters.apresentadores;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.CategoriaApresentador;
import java.util.List;

public class CategoriaApresentadorImpl implements CategoriaApresentador {

    @Override
    public CategoriaSaidaDTO formatar(List<Categoria> categorias) {
        List<String> nomeCategorias = categorias.stream().map(Categoria::name).toList();
        return new CategoriaSaidaDTO(nomeCategorias);
    }
}
