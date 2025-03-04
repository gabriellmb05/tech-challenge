package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.BuscaProdutosCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaProdutosCasoDeUsoImpl implements BuscaProdutosCasoDeUso {

    private final ProdutoGateway produtoGateway;

    public BuscaProdutosCasoDeUsoImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable) {
        return this.produtoGateway.listarComFiltros(filtro, pageable);
    }
}
