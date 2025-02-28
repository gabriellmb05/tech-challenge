package br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import java.util.List;

public interface CategoriaApresentador {
    CategoriaSaidaDTO formatar(List<Categoria> categorias);
}
