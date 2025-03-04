package br.com.on.fiap.adaptadores.produto.entrada;

import br.com.on.fiap.adaptadores.pedido.entrada.mapeador.ProdutoFiltroEntradaMapeador;
import br.com.on.fiap.adaptadores.produto.ProdutoControladorSwagger;
import br.com.on.fiap.adaptadores.produto.entrada.dto.filtro.ProdutoFiltroDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.resposta.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.produto.entrada.dto.solicitacao.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adaptadores.produto.entrada.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.*;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
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

    private final BuscaProdutoPorIdCasoDeUso buscaProdutoPorIdCasoDeUso;
    private final InsereProdutoCasoDeUso insereProdutoCasoDeUso;
    private final AlteraProdutoCasoDeUso alteraProdutoCasoDeUso;
    private final DeletaProdutoCasoDeUso deletaProdutoCasoDeUso;
    private final ProdutoEntradaMapeador produtoEntradaMapeador;
    private final ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador;
    private final BuscaProdutosCasoDeUso buscaProdutosCasoDeUso;

    public ProdutoManipulador(
            BuscaProdutoPorIdCasoDeUso buscaProdutoPorIdCasoDeUso,
            InsereProdutoCasoDeUso insereProdutoCasoDeUso,
            AlteraProdutoCasoDeUso alteraProdutoCasoDeUso,
            DeletaProdutoCasoDeUso deletaProdutoCasoDeUso,
            ProdutoEntradaMapeador produtoEntradaMapeador,
            ProdutoFiltroEntradaMapeador produtoFiltroEntradaMapeador,
            BuscaProdutosCasoDeUso buscaProdutosCasoDeUso) {
        this.buscaProdutoPorIdCasoDeUso = buscaProdutoPorIdCasoDeUso;
        this.insereProdutoCasoDeUso = insereProdutoCasoDeUso;
        this.alteraProdutoCasoDeUso = alteraProdutoCasoDeUso;
        this.deletaProdutoCasoDeUso = deletaProdutoCasoDeUso;
        this.produtoEntradaMapeador = produtoEntradaMapeador;
        this.produtoFiltroEntradaMapeador = produtoFiltroEntradaMapeador;
        this.buscaProdutosCasoDeUso = buscaProdutosCasoDeUso;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(@PathVariable("id") Long id) {
        Produto produto = buscaProdutoPorIdCasoDeUso.buscar(id);
        ProdutoRespostaDTO produtoRespostaDTO = produtoEntradaMapeador.paraProdutoDTO(produto);
        return ResponseEntity.ok().body(produtoRespostaDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<ProdutoRespostaDTO>> listarProdutosComFiltro(
            @ParameterObject ProdutoFiltroDTO filtro, Pageable pageable) {
        ProdutoFiltro produtoFiltro = produtoFiltroEntradaMapeador.paraProdutoFiltro(filtro);
        Page<ProdutoRespostaDTO> produtos = buscaProdutosCasoDeUso
                .listarComFiltro(produtoFiltro, pageable)
                .map(produtoEntradaMapeador::paraProdutoDTO);
        return ResponseEntity.ok().body(new PagedModel<>(produtos));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoRespostaDTO> insereProduto(
            @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = insereProdutoCasoDeUso.inserir(produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoPersistidoDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRespostaDTO> alteraProduto(
            @PathVariable("id") Long id, @Valid @RequestBody ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = produtoEntradaMapeador.paraProduto(produtoSolicitacaoDTO);
        Produto produtoPersistido = alteraProdutoCasoDeUso.alterar(id, produto);
        ProdutoRespostaDTO produtoPersistidoDTO = produtoEntradaMapeador.paraProdutoDTO(produtoPersistido);
        return ResponseEntity.ok().body(produtoPersistidoDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable("id") Long id) {
        deletaProdutoCasoDeUso.deleta(id);
        return ResponseEntity.noContent().build();
    }
}
