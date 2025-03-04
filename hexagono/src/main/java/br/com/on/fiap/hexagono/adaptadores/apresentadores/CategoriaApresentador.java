package br.com.on.fiap.hexagono.adaptadores.apresentadores;

import br.com.on.fiap.hexagono.adaptadores.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.entidades.Categoria;
import java.util.List;

public interface CategoriaApresentador {
    CategoriaSaidaDTO formatar(List<Categoria> categorias);
}
