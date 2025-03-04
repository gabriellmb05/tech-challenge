package br.com.on.fiap.hexagono.adaptadores.apresentadores.impl;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.CategoriaApresentador;
import br.com.on.fiap.hexagono.adaptadores.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.entidades.Categoria;
import java.util.List;

public class CategoriaApresentadorImpl implements CategoriaApresentador {

    @Override
    public CategoriaSaidaDTO formatar(List<Categoria> categorias) {
        List<String> nomeCategorias = categorias.stream().map(Categoria::name).toList();
        return new CategoriaSaidaDTO(nomeCategorias);
    }
}
