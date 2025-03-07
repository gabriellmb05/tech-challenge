package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroEntradaRequest;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoRequest;
import br.com.on.fiap.adapter.input.dto.response.PaginacaoRespostaInfo;
import br.com.on.fiap.adapter.input.swagger.ProdutoApiSwagger;
import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.produto.ProdutoResposta;
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
    public ResponseEntity<PaginaResposta<ProdutoResposta>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroEntradaRequest filtro, Pageable pageable) {
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaInfo.from(pageable);
        PaginaResposta<ProdutoResposta> produtoPaginaResposta =
                produtoController.listarProdutosComFiltro(filtro, paginacaoResposta);
        return ResponseEntity.ok().body(produtoPaginaResposta);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResposta> insereProduto(
            @Valid @RequestBody ProdutoSolicitacaoRequest produtoSolicitacaoRequestDTO) {
        ProdutoResposta produtoPersistido = produtoController.insereProduto(produtoSolicitacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResposta> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoRequest produtoSolicitacaoRequestDTO) {
        ProdutoResposta produtoAlterado = produtoController.alteraProduto(id, produtoSolicitacaoRequestDTO);
        return ResponseEntity.ok().body(produtoAlterado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoController.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
