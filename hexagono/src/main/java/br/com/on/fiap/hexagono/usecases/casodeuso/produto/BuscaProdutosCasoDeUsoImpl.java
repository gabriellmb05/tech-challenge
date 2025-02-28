package br.com.on.fiap.hexagono.usecases.casodeuso.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.entities.entidades.ProdutoFiltro;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.BuscaProdutosCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaProdutosCasoDeUsoImpl implements BuscaProdutosCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public BuscaProdutosCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable) {
        return this.persisteProdutoPortaSaida.listarComFiltros(filtro, pageable);
    }
}
