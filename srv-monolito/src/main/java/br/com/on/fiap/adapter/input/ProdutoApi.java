package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroDTO;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.response.ProdutoRespostaDTO;
import br.com.on.fiap.adapter.input.mapper.ProdutoFiltroInputMapper;
import br.com.on.fiap.adapter.input.mapper.ProdutoInputMapper;
import br.com.on.fiap.adapter.input.swagger.ProdutoApiSwagger;
import br.com.on.fiap.hexagono.application.usecase.produto.*;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
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
public class ProdutoApi implements ProdutoApiSwagger {

    private final ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;
    private final ProdutoInsereUseCase produtoInsereUseCase;
    private final ProdutoAlteraUseCase produtoAlteraUseCase;
    private final ProdutoDeletaUseCase produtoDeletaUseCase;
    private final ProdutoInputMapper produtoInputMapper;
    private final ProdutoFiltroInputMapper produtoFiltroInputMapper;
    private final ProdutoListaUseCase produtoListaUseCase;

    public ProdutoApi(
            ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase,
            ProdutoInsereUseCase produtoInsereUseCase,
            ProdutoAlteraUseCase produtoAlteraUseCase,
            ProdutoDeletaUseCase produtoDeletaUseCase,
            ProdutoInputMapper produtoInputMapper,
            ProdutoFiltroInputMapper produtoFiltroInputMapper,
            ProdutoListaUseCase produtoListaUseCase) {
        this.produtoBuscaPorIdUseCase = produtoBuscaPorIdUseCase;
        this.produtoInsereUseCase = produtoInsereUseCase;
        this.produtoAlteraUseCase = produtoAlteraUseCase;
        this.produtoDeletaUseCase = produtoDeletaUseCase;
        this.produtoInputMapper = produtoInputMapper;
        this.produtoFiltroInputMapper = produtoFiltroInputMapper;
        this.produtoListaUseCase = produtoListaUseCase;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
        Produto produto = produtoBuscaPorIdUseCase.buscar(id);
        ProdutoRespostaDTO produtoRespostaDTO = produtoInputMapper.paraProdutoDTO(produto);
        return ResponseEntity.ok().body(produtoRespostaDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<ProdutoRespostaDTO>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroDTO filtro, Pageable pageable) {
        ProdutoFiltro produtoFiltro = produtoFiltroInputMapper.paraProdutoFiltro(filtro);
        Page<ProdutoRespostaDTO> produtos =
                produtoListaUseCase.listarComFiltro(produtoFiltro, pageable).map(produtoInputMapper::paraProdutoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(produtos));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoRespostaDTO> insereProduto(
            @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoInputMapper.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = produtoInsereUseCase.inserir(produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoInputMapper.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoInputMapper.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = produtoAlteraUseCase.alterar(id, produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoInputMapper.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.ok().body(produtoPersistidoDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        produtoDeletaUseCase.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
