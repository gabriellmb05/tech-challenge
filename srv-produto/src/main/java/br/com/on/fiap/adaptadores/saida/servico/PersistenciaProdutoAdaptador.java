package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
  public Produto salvaProduto(Produto produto) {
    ProdutoEntidade produtoEntidade = produtoSaidaMapeador.paraEntidade(produto);
    ProdutoEntidade produtoPersistido = produtoRepositorio.save(produtoEntidade);
    return produtoSaidaMapeador.paraProduto(produtoPersistido);
  }

  @Override
  public Optional<Produto> buscaProdutoPorNome(String nome) {
    return produtoRepositorio.findByNome(nome).map(produtoSaidaMapeador::paraProduto);
  }

  @Override
  public void deletaProdutoPorId(Long id) {
    produtoRepositorio.deleteById(id);
  }

  @Override
  public Page<Produto> listarTodosProdutos(Pageable page) {
    List<Produto> produtos = produtoRepositorio
            .findAll(page)
            .stream()
            .map(produtoSaidaMapeador::paraProduto)
            .collect(Collectors.toList());
    return new PageImpl<>(produtos, page, produtos.size());
  }

  @Override
  public Page<Produto> listarProdutosPorCategoria(Categoria categoria, Pageable page) {
    List<Produto> produtos = produtoRepositorio
            .findByCategoria(categoria, page)
            .stream()
            .map(produtoSaidaMapeador::paraProduto)
            .collect(Collectors.toList());
    return new PageImpl<>(produtos, page, produtos.size());
  }
}
