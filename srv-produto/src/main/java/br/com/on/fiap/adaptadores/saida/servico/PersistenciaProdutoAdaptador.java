package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaProdutoAdaptador implements PersisteProdutoPortaSaida {

	private final ProdutoRepositorio produtoRepositorio;
	private final ProdutoSaidaMapeador produtoSaidaMapeador;

	public PersistenciaProdutoAdaptador(ProdutoRepositorio produtoRepositorio,
			ProdutoSaidaMapeador produtoSaidaMapeador) {
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
}
