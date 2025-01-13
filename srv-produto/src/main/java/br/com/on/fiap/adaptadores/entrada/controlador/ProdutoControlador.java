package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PaginacaoMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.portas.entrada.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador implements ProdutoControladorSwagger {
  private final BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;
  private final InsereProdutoPortaEntrada insereProdutoPortaEntrada;
  private final AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;
  private final DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;
  private final ProdutoEntradaMapeador produtoEntradaMapeador;
  private final ListarProdutoPortaEntrada listarProdutoPortaEntrada;
  public ProdutoControlador(
          BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada,
          InsereProdutoPortaEntrada insereProdutoPortaEntrada,
          AlteraProdutoPortaEntrada alteraProdutoPortaEntrada,
          DeletaProdutoPortaEntrada deletaProdutoPortaEntrada,
          ProdutoEntradaMapeador produtoEntradaMapeador, ListarProdutoPortaEntrada listarProdutoPortaEntrada) {
    this.buscaProdutoPorIdPortaEntrada = buscaProdutoPorIdPortaEntrada;
    this.insereProdutoPortaEntrada = insereProdutoPortaEntrada;
    this.alteraProdutoPortaEntrada = alteraProdutoPortaEntrada;
    this.deletaProdutoPortaEntrada = deletaProdutoPortaEntrada;
    this.produtoEntradaMapeador = produtoEntradaMapeador;
      this.listarProdutoPortaEntrada = listarProdutoPortaEntrada;
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
  public ResponseEntity<Page<ProdutoRespostaDTO>> listarProdutosPorCategoria(
          @RequestParam(value = "categoria", required = false) String categoria,
          @RequestParam(name = "page", required = false, defaultValue = "0") int page,
          @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
      Page<Produto> produtos = null;
      if(categoria == null){
        produtos = listarProdutoPortaEntrada.listarTodosProdutos(PageRequest.of(page, size));
      }else{
        produtos = listarProdutoPortaEntrada.listarPorCategoria(Categoria.buscaCategoria(categoria), PageRequest.of(page, size));
      }
      Page<ProdutoRespostaDTO> produtoRespostaDTOS = PaginacaoMapeador.paraPageProdutoRespostaDTO(produtos, produtoEntradaMapeador);
      return ResponseEntity.ok().body(produtoRespostaDTOS);
  }

  @Override
  @PostMapping
  public ResponseEntity<ProdutoRespostaDTO> insereProduto(
      @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
    Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
    Produto produtoPersistido = insereProdutoPortaEntrada.inserir(produto);
    ProdutoRespostaDTO produtoPersistidoDTO =
        produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
      @PathVariable("id") Long id,
      @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
    Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
    Produto produtoPersistido = alteraProdutoPortaEntrada.alterar(id, produto);
    ProdutoRespostaDTO produtoPersistidoDTO =
        produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
    return ResponseEntity.ok().body(produtoPersistidoDTO);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
    deletaProdutoPortaEntrada.deleta(id);
    return ResponseEntity.noContent().build();
  }
}
