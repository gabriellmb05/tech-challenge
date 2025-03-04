package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.casodeuso.produto.entrada.BuscaProdutosCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
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
