package br.com.on.fiap.adaptadores.saida.persistencia;

import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoEntidadeMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersistenciaProdutoAdapter implements PersisteProdutoPortaSaida {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Override
    public Optional<Produto> buscaProdutoPorId(Long id) {
        Optional<ProdutoEntidade> produtoEntidadePorId = produtoRepositorio.findById(id);
        if(produtoEntidadePorId.isEmpty()) return Optional.empty();
        Produto produto = ProdutoEntidadeMapeador.produtoEntidadeParaProduto(produtoEntidadePorId.get());
        return Optional.of(produto);
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntidade produtoEntidade = ProdutoEntidadeMapeador.produtoParaProdutoEntidade(produto);
        ProdutoEntidade produtoPersistido = produtoRepositorio.save(produtoEntidade);
        return ProdutoEntidadeMapeador.produtoEntidadeParaProduto(produtoPersistido);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        Optional<ProdutoEntidade> produtoEntidadePorId = produtoRepositorio.findByNome(nome);
        if(produtoEntidadePorId.isEmpty()) return Optional.empty();
        Produto produto = ProdutoEntidadeMapeador.produtoEntidadeParaProduto(produtoEntidadePorId.get());
        return Optional.of(produto);
    }
}
