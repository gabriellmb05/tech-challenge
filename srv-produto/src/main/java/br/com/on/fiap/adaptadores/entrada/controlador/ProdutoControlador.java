package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.CategoriasDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoFiltroMapeador;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.produto.*;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador implements ProdutoControladorSwagger {

	private final BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;
	private final InsereProdutoPortaEntrada insereProdutoPortaEntrada;
	private final AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;
	private final DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;
	private final ProdutoEntradaMapeador produtoEntradaMapeador;
	private final ProdutoFiltroMapeador produtoFiltroMapeador;
	private final ListarProdutoPortaEntrada listarProdutoPortaEntrada;
	private final BuscaCategoriaPortaEntrada buscaCategoriaPortaEntrada;

	public ProdutoControlador(BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada,
			InsereProdutoPortaEntrada insereProdutoPortaEntrada, AlteraProdutoPortaEntrada alteraProdutoPortaEntrada,
			DeletaProdutoPortaEntrada deletaProdutoPortaEntrada, ProdutoEntradaMapeador produtoEntradaMapeador,
			ProdutoFiltroMapeador produtoFiltroMapeador, ListarProdutoPortaEntrada listarProdutoPortaEntrada,
			BuscaCategoriaPortaEntrada buscaCategoriaPortaEntrada) {
		this.buscaProdutoPorIdPortaEntrada = buscaProdutoPorIdPortaEntrada;
		this.insereProdutoPortaEntrada = insereProdutoPortaEntrada;
		this.alteraProdutoPortaEntrada = alteraProdutoPortaEntrada;
		this.deletaProdutoPortaEntrada = deletaProdutoPortaEntrada;
		this.produtoEntradaMapeador = produtoEntradaMapeador;
		this.produtoFiltroMapeador = produtoFiltroMapeador;
		this.listarProdutoPortaEntrada = listarProdutoPortaEntrada;
		this.buscaCategoriaPortaEntrada = buscaCategoriaPortaEntrada;
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
		Produto produto = buscaProdutoPorIdPortaEntrada.buscar(id);
		ProdutoRespostaDTO produtoRespostaDTO = produtoEntradaMapeador.paraProdutoDTO(produto);
		return ResponseEntity.ok().body(produtoRespostaDTO);
	}

	@Override
	@GetMapping
	public ResponseEntity<Page<ProdutoRespostaDTO>> listarProdutosComFiltro(@ParameterObject ProdutoFiltroDTO filtro,
			Pageable pageable) {
		ProdutoFiltro produtoFiltro = produtoFiltroMapeador.paraProdutoFiltro(filtro);
		Page<ProdutoRespostaDTO> produtos = listarProdutoPortaEntrada.listarComFiltro(produtoFiltro, pageable)
				.map(produtoEntradaMapeador::paraProdutoDTO);
		return ResponseEntity.ok().body(produtos);
	}

	@Override
	@PostMapping
	public ResponseEntity<ProdutoRespostaDTO> insereProduto(
			@Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
		Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
		Produto produtoPersistido = insereProdutoPortaEntrada.inserir(produto);
		ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoRespostaDTO> alteraProduto(@PathVariable("id") Long id,
			@Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
		Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
		Produto produtoPersistido = alteraProdutoPortaEntrada.alterar(id, produto);
		ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
		return ResponseEntity.ok().body(produtoPersistidoDTO);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
		deletaProdutoPortaEntrada.deleta(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/categorias")
	public ResponseEntity<CategoriasDTO> buscaCategorias() {
		List<String> categorias = buscaCategoriaPortaEntrada.buscaCategorias().stream().map(Categoria::getNome)
				.toList();
		return ResponseEntity.ok().body(new CategoriasDTO(categorias));
	}
}
