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
        return produtoRepositorio.findById(id)
                .map(ProdutoEntidadeMapeador::produtoEntidadeParaProduto);
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntidade produtoEntidade = ProdutoEntidadeMapeador.produtoParaProdutoEntidade(produto);
        ProdutoEntidade produtoPersistido = produtoRepositorio.save(produtoEntidade);
        return ProdutoEntidadeMapeador.produtoEntidadeParaProduto(produtoPersistido);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepositorio.findByNome(nome)
                .map(ProdutoEntidadeMapeador::produtoEntidadeParaProduto);
    }
}
