package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaProdutoAdaptador implements PersisteProdutoPortaSaida {

    private final ProdutoRepositorio produtoRepositorio;
    private final ProdutoSaidaMapeador produtoSaidaMapeador;

    @Autowired
    public PersistenciaProdutoAdaptador(
            ProdutoRepositorio produtoRepositorio, ProdutoSaidaMapeador produtoSaidaMapeador) {
        this.produtoRepositorio = produtoRepositorio;
        this.produtoSaidaMapeador = produtoSaidaMapeador;
    }

    @Override
    public Optional<Produto> buscaProdutoPorId(Long id) {
        return produtoRepositorio.findById(id).map(produtoSaidaMapeador::paraProduto);
    }

    @Override
    public List<Produto> buscaProdutoPorIdsLista(List<Long> ids) {
        return produtoSaidaMapeador.paraListaProdutos(produtoRepositorio.findByProIdIn(ids));
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntidade produtoEntidade = produtoSaidaMapeador.paraEntidade(produto);
        ProdutoEntidade produtoPersistido = produtoRepositorio.save(produtoEntidade);
        return produtoSaidaMapeador.paraProduto(produtoPersistido);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepositorio.findByNmNome(nome).map(produtoSaidaMapeador::paraProduto);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        produtoRepositorio.deleteById(id);
    }

    @Override
    public Page<Produto> listarComFiltros(ProdutoFiltro filtro, Pageable page) {
        return produtoRepositorio.buscarComFiltro(filtro, page).map(produtoSaidaMapeador::paraProduto);
    }
}
