package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaProdutosPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaProdutosCasoDeUso implements BuscaProdutosPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public BuscaProdutosCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable) {
        return this.persisteProdutoPortaSaida.listarComFiltros(filtro, pageable);
    }
}
