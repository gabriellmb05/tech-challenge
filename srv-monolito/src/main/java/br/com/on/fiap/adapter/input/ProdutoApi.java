package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroDTO;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.swagger.ProdutoApiSwagger;
import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.application.dto.*;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
        ProdutoRespostaDTO produtoRespostaDTO = produtoController.buscaProdutoPorId(id);
        return ResponseEntity.ok().body(produtoRespostaDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<Pagina<ProdutoRespostaDTO>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroDTO filtro, Pageable pageable) {
        Paginacao paginacao = new Paginacao();
        paginacao.setPagina(pageable.getPageNumber());
        paginacao.setTamanhoPagina(pageable.getPageSize());

        Optional<Sort.Order> first = pageable.getSort().get().findFirst();
        first.ifPresent(order -> {
            Ordenacao ordenacao = new Ordenacao();
            ordenacao.setCampo(order.getProperty());
            ordenacao.setDirecao(Direcao.valueOf(order.getDirection().name()));
            paginacao.setOrdenacao(ordenacao);
        });

        Pagina<ProdutoRespostaDTO> produtoPagina = produtoController.listarProdutosComFiltro(filtro, paginacao);

        return ResponseEntity.ok().body(produtoPagina);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoRespostaDTO> insereProduto(
            @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        ProdutoRespostaDTO produtoPersistido = produtoController.insereProduto(produtoSolicitacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistido);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        ProdutoRespostaDTO produtoAlterado = produtoController.alteraProduto(id, produtoSolicitacaoDTO);
        return ResponseEntity.ok().body(produtoAlterado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoController.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
