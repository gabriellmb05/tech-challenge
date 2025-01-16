package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoSaidaMapeador {

	Produto paraProduto(ProdutoEntidade produtoEntidade);

	List<Produto> paraProduto(List<ProdutoEntidade> produtoEntidade);

	ProdutoEntidade paraEntidade(Produto produto);
}
