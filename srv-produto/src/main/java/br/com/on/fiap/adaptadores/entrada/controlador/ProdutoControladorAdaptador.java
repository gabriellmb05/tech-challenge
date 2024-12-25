package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoMapeador;
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
public class ProdutoControladorAdaptador {
    private final BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;
    private final InsereProdutoPortaEntrada insereProdutoPortaEntrada;
    private final AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;
    private final DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;

    public ProdutoControladorAdaptador(BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada, InsereProdutoPortaEntrada insereProdutoPortaEntrada, AlteraProdutoPortaEntrada alteraProdutoPortaEntrada, DeletaProdutoPortaEntrada deletaProdutoPortaEntrada) {
        this.buscaProdutoPorIdPortaEntrada = buscaProdutoPorIdPortaEntrada;
        this.insereProdutoPortaEntrada = insereProdutoPortaEntrada;
        this.alteraProdutoPortaEntrada = alteraProdutoPortaEntrada;
        this.deletaProdutoPortaEntrada = deletaProdutoPortaEntrada;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscaProdutoPorId(@PathVariable("id") long id) {
        Produto produto = buscaProdutoPorIdPortaEntrada.buscar(id);
        ProdutoDTO produtoDTO = ProdutoMapeador.produtoParaProdutoDTO(produto);
        return ResponseEntity.ok().body(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> InsereProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapeador.produtoDTOParaProduto(produtoDTO);
        Produto produtoPersistido = insereProdutoPortaEntrada.inserir(produto);
        ProdutoDTO produtoPersistidoDTO = ProdutoMapeador.produtoParaProdutoDTO(produtoPersistido);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> alteraProduto(@PathVariable("id") long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapeador.produtoDTOParaProduto(produtoDTO);
        Produto produtoPersistido = alteraProdutoPortaEntrada.alterar(id, produto);
        ProdutoDTO produtoPersistidoDTO = ProdutoMapeador.produtoParaProdutoDTO(produtoPersistido);
        return ResponseEntity.ok().body(produtoPersistidoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDTO> deletaProduto(@PathVariable("id") long id) {
        deletaProdutoPortaEntrada.deleta(id);
        return ResponseEntity.noContent().build();
    }
}