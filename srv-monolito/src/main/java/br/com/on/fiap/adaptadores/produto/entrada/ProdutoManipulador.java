package br.com.on.fiap.adaptadores.produto.entrada;

import br.com.on.fiap.adaptadores.pedido.entrada.mapeador.ProdutoFiltroEntradaMapeador;
import br.com.on.fiap.adaptadores.produto.ProdutoControladorSwagger;
import br.com.on.fiap.adaptadores.produto.entrada.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.produto.entrada.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import br.com.on.fiap.hexagono.usecase.produto.base.*;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoManipulador implements ProdutoControladorSwagger {

    private final ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;
    private final ProdutoInsereUseCase produtoInsereUseCase;
    private final ProdutoAlteraUseCase produtoAlteraUseCase;
    private final ProdutoDeletaUseCase produtoDeletaUseCase;
    private final ProdutoEntradaMapeador produtoEntradaMapeador;
    private final ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador;
    private final ProdutoListaUseCase produtoListaUseCase;

    public ProdutoManipulador(
            ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase,
            ProdutoInsereUseCase produtoInsereUseCase,
            ProdutoAlteraUseCase produtoAlteraUseCase,
            ProdutoDeletaUseCase produtoDeletaUseCase,
            ProdutoEntradaMapeador produtoEntradaMapeador,
            ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador,
            ProdutoListaUseCase produtoListaUseCase) {
        this.produtoBuscaPorIdUseCase = produtoBuscaPorIdUseCase;
        this.produtoInsereUseCase = produtoInsereUseCase;
        this.produtoAlteraUseCase = produtoAlteraUseCase;
        this.produtoDeletaUseCase = produtoDeletaUseCase;
        this.produtoEntradaMapeador = produtoEntradaMapeador;
        this.produtoFiltroEntradaMapeador = produtoFiltroEntradaMapeador;
        this.produtoListaUseCase = produtoListaUseCase;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
        Produto produto = produtoBuscaPorIdUseCase.buscar(id);
        ProdutoRespostaDTO produtoRespostaDTO = produtoEntradaMapeador.paraProdutoDTO(produto);
        return ResponseEntity.ok().body(produtoRespostaDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<ProdutoRespostaDTO>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroDTO filtro, Pageable pageable) {
        ProdutoFiltro produtoFiltro = produtoFiltroEntradaMapeador.paraProdutoFiltro(filtro);
        Page<ProdutoRespostaDTO> produtos = produtoListaUseCase
                .listarComFiltro(produtoFiltro, pageable)
                .map(produtoEntradaMapeador::paraProdutoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(produtos));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoRespostaDTO> insereProduto(
            @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = produtoInsereUseCase.inserir(produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = produtoAlteraUseCase.alterar(id, produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.ok().body(produtoPersistidoDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoDeletaUseCase.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
