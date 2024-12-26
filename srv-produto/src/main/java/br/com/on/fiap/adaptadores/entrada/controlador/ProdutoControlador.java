package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import jakarta.validation.Valid;
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

  public ProdutoControlador(
      BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada,
      InsereProdutoPortaEntrada insereProdutoPortaEntrada,
      AlteraProdutoPortaEntrada alteraProdutoPortaEntrada,
      DeletaProdutoPortaEntrada deletaProdutoPortaEntrada,
      ProdutoEntradaMapeador produtoEntradaMapeador) {
    this.buscaProdutoPorIdPortaEntrada = buscaProdutoPorIdPortaEntrada;
    this.insereProdutoPortaEntrada = insereProdutoPortaEntrada;
    this.alteraProdutoPortaEntrada = alteraProdutoPortaEntrada;
    this.deletaProdutoPortaEntrada = deletaProdutoPortaEntrada;
    this.produtoEntradaMapeador = produtoEntradaMapeador;
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ProdutoDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
    Produto produto = buscaProdutoPorIdPortaEntrada.buscar(id);
    ProdutoDTO produtoDTO = produtoEntradaMapeador.paraProdutoDTO(produto);
    return ResponseEntity.ok().body(produtoDTO);
  }

  @Override
  @PostMapping
  public ResponseEntity<ProdutoDTO> insereProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
    Produto produto = produtoEntradaMapeador.paraProduto(produtoDTO);
    Produto produtoPersistido = insereProdutoPortaEntrada.inserir(produto);
    ProdutoDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<ProdutoDTO> alteraProduto(
      @PathVariable("id") Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
    Produto produto = produtoEntradaMapeador.paraProduto(produtoDTO);
    Produto produtoPersistido = alteraProdutoPortaEntrada.alterar(id, produto);
    ProdutoDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
    return ResponseEntity.ok().body(produtoPersistidoDTO);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<ProdutoDTO> deletaProduto(@PathVariable("id") Long id) {
    deletaProdutoPortaEntrada.deleta(id);
    return ResponseEntity.noContent().build();
  }
}
