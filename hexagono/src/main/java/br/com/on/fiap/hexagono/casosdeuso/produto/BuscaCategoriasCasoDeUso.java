package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaCategoriaPortaEntrada;
import java.util.Arrays;
import java.util.List;

public class BuscaCategoriasCasoDeUso implements BuscaCategoriaPortaEntrada {

    @Override
    public List<Categoria> buscaCategorias() {
        return Arrays.stream(Categoria.values()).toList();
    }
}
