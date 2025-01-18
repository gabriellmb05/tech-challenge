package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.filtro.ProdutoFiltroEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.swagger.ProdutoControladorSwagger;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import br.com.on.fiap.hexagono.portas.entrada.produto.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoControlador implements ProdutoControladorSwagger {

    private final BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;
    private final InsereProdutoPortaEntrada insereProdutoPortaEntrada;
    private final AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;
    private final DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;
    private final ProdutoEntradaMapeador produtoEntradaMapeador;
    private final ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador;
    private final BuscaProdutosPortaEntrada buscaProdutosPortaEntrada;
    private final BuscaCategoriaPortaEntrada buscaCategoriaPortaEntrada;

    public ProdutoControlador(
            BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada,
            InsereProdutoPortaEntrada insereProdutoPortaEntrada,
            AlteraProdutoPortaEntrada alteraProdutoPortaEntrada,
            DeletaProdutoPortaEntrada deletaProdutoPortaEntrada,
            ProdutoEntradaMapeador produtoEntradaMapeador,
            ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador,
            BuscaProdutosPortaEntrada buscaProdutosPortaEntrada,
            BuscaCategoriaPortaEntrada buscaCategoriaPortaEntrada) {
        this.buscaProdutoPorIdPortaEntrada = buscaProdutoPorIdPortaEntrada;
        this.insereProdutoPortaEntrada = insereProdutoPortaEntrada;
        this.alteraProdutoPortaEntrada = alteraProdutoPortaEntrada;
        this.deletaProdutoPortaEntrada = deletaProdutoPortaEntrada;
        this.produtoEntradaMapeador = produtoEntradaMapeador;
        this.produtoFiltroEntradaMapeador = produtoFiltroEntradaMapeador;
        this.buscaProdutosPortaEntrada = buscaProdutosPortaEntrada;
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
    public ResponseEntity<PagedModel<ProdutoRespostaDTO>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroDTO filtro, Pageable pageable) {
        ProdutoFiltro produtoFiltro = produtoFiltroEntradaMapeador.paraProdutoFiltro(filtro);
        Page<ProdutoRespostaDTO> produtos = buscaProdutosPortaEntrada
                .listarComFiltro(produtoFiltro, pageable)
                .map(produtoEntradaMapeador::paraProdutoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(produtos));
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
    public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
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
    public ResponseEntity<List<String>> buscaCategorias() {
        List<String> categorias = buscaCategoriaPortaEntrada.buscaCategorias().stream()
                .map(Categoria::name)
                .toList();
        return ResponseEntity.ok().body(categorias);
    }
}
