package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.entrada.ProdutoRequest;
import br.com.on.fiap.adapter.input.dto.filtro.ProdutoFiltroRequest;
import br.com.on.fiap.adapter.input.dto.resposta.PaginacaoRespostaInfo;
import br.com.on.fiap.adapter.input.swagger.ProdutoApiSwagger;
import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
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
            @ParameterObject ProdutoFiltroRequest filtro, Pageable pageable) {
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaInfo.from(pageable);
        PaginaResposta<ProdutoResposta> produtoPaginaResposta =
                produtoController.listarProdutosComFiltro(filtro, paginacaoResposta);
        return ResponseEntity.ok().body(produtoPaginaResposta);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResposta> insereProduto(@Valid @RequestBody ProdutoRequest produtoRequestDTO) {
        ProdutoResposta produtoPersistido = produtoController.insereProduto(produtoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResposta> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoRequest produtoRequestDTO) {
        ProdutoResposta produtoAlterado = produtoController.alteraProduto(id, produtoRequestDTO);
        return ResponseEntity.ok().body(produtoAlterado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoController.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
