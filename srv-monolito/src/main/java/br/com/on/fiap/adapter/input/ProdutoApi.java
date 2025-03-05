package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroRequest;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacao;
import br.com.on.fiap.adapter.input.dto.response.PaginacaoResponse;
import br.com.on.fiap.adapter.input.swagger.ProdutoApiSwagger;
import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.domain.model.*;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoApi implements ProdutoApiSwagger {

    private final ProdutoController produtoController;

    public ProdutoApi(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResposta> buscaProdutoPorId(@PathVariable("id") Long id) {
        ProdutoResposta produtoResposta = produtoController.buscaProdutoPorId(id);
        return ResponseEntity.ok().body(produtoResposta);
    }

    @Override
    @GetMapping
    public ResponseEntity<Pagina<ProdutoResposta>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroRequest filtro, Pageable pageable) {
        Paginacao paginacao = PaginacaoResponse.from(pageable);
        Pagina<ProdutoResposta> produtoPagina = produtoController.listarProdutosComFiltro(filtro, paginacao);
        return ResponseEntity.ok().body(produtoPagina);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResposta> insereProduto(@Valid @RequestBody ProdutoSolicitacao produtoSolicitacaoDTO) {
        ProdutoResposta produtoPersistido = produtoController.insereProduto(produtoSolicitacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResposta> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacao produtoSolicitacaoDTO) {
        ProdutoResposta produtoAlterado = produtoController.alteraProduto(id, produtoSolicitacaoDTO);
        return ResponseEntity.ok().body(produtoAlterado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoController.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
