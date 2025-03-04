package br.com.on.fiap.adapter.produto.entrada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adapter.input.ProdutoApi;
import br.com.on.fiap.adapter.input.mapper.ProdutoFiltroInputMapper;
import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroDTO;
import br.com.on.fiap.adapter.input.dto.response.ProdutoRespostaDTO;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.mapper.ProdutoInputMapper;
import br.com.on.fiap.datapool.*;
import br.com.on.fiap.hexagono.application.usecase.produto.*;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import br.com.on.fiap.hexagono.usecase.categoria.entrada.BuscaCategoriaCasoDeUso;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProdutoApiTest {

    @Mock
    private ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;

    @Mock
    private ProdutoInsereUseCase produtoInsereUseCase;

    @Mock
    private ProdutoAlteraUseCase produtoAlteraUseCase;

    @Mock
    private ProdutoDeletaUseCase produtoDeletaUseCase;

    @Mock
    private ProdutoInputMapper produtoInputMapper;

    @Mock
    private ProdutoFiltroInputMapper produtoFiltroInputMapper;

    @Mock
    private ProdutoListaUseCase produtoListaUseCase;

    @Mock
    private BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso;

    @InjectMocks
    private ProdutoApi produtoControlador;

    static Stream<Arguments> produtoDTOProvider() {
        return Stream.of(
                Arguments.of(DataPoolProdutoRespostaDTO.gerarProduto1(), DataPoolProdutoSolicitacaoDTO.gerarProduto1()),
                Arguments.of(DataPoolProdutoRespostaDTO.gerarProduto2(), DataPoolProdutoSolicitacaoDTO.gerarProduto2()),
                Arguments.of(
                        DataPoolProdutoRespostaDTO.gerarProduto3(), DataPoolProdutoSolicitacaoDTO.gerarProduto3()));
    }

    static Stream<Arguments> produtoFiltroProvider() {
        return Stream.of(
                Arguments.of(
                        DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(),
                        DataPoolProdutoFiltro.gerarProdutoXBurguer(),
                        Collections.emptyList(),
                        Collections.emptyList()),
                Arguments.of(
                        DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(),
                        DataPoolProdutoFiltro.gerarProdutoXBurguer(),
                        List.of(DataPoolProduto.gerarProdutoXBurguer()),
                        List.of(DataPoolProdutoRespostaDTO.gerarProdutoXBurguer())),
                Arguments.of(
                        DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(),
                        DataPoolProdutoFiltro.gerarProdutoXBurguer(),
                        DataPoolProduto.gerarListaProdutos(),
                        DataPoolProdutoRespostaDTO.gerarListaProdutoRespostaDTO()));
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
    void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado(ProdutoRespostaDTO produtoRespostaDTO) {
        Long id = produtoRespostaDTO.getId();
        Produto produto = new Produto();
        when(produtoBuscaPorIdUseCase.buscar(id)).thenReturn(produto);
        when(produtoInputMapper.paraProdutoDTO(produto)).thenReturn(produtoRespostaDTO);

        ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.buscaProdutoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoRespostaDTO, response.getBody());
        verify(produtoBuscaPorIdUseCase).buscar(id);
        verify(produtoInputMapper).paraProdutoDTO(produto);
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
    void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo(
            ProdutoRespostaDTO produtoRespostaDTO, ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Produto produto = new Produto();
        Produto produtoPersistido = new Produto();
        when(produtoInputMapper.paraProduto(produtoSolicitacaoDTO)).thenReturn(produto);
        when(produtoInsereUseCase.inserir(produto)).thenReturn(produtoPersistido);
        when(produtoInputMapper.paraProdutoDTO(produtoPersistido)).thenReturn(produtoRespostaDTO);

        ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.insereProduto(produtoSolicitacaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produtoRespostaDTO, response.getBody());
        verify(produtoInputMapper).paraProduto(produtoSolicitacaoDTO);
        verify(produtoInsereUseCase).inserir(produto);
        verify(produtoInputMapper).paraProdutoDTO(produtoPersistido);
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
    void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado(
            ProdutoRespostaDTO produtoRespotaDTO, ProdutoSolicitacaoDTO produtoSolicitacaoDTO) {
        Long id = produtoRespotaDTO.getId();
        Produto produto = new Produto();
        Produto produtoPersistido = new Produto();

        when(produtoInputMapper.paraProduto(produtoSolicitacaoDTO)).thenReturn(produto);
        when(produtoAlteraUseCase.alterar(id, produto)).thenReturn(produtoPersistido);
        when(produtoInputMapper.paraProdutoDTO(produtoPersistido)).thenReturn(produtoRespotaDTO);

        ResponseEntity<ProdutoRespostaDTO> response = produtoControlador.alteraProduto(id, produtoSolicitacaoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoRespotaDTO, response.getBody());
        verify(produtoInputMapper).paraProduto(produtoSolicitacaoDTO);
        verify(produtoAlteraUseCase).alterar(id, produto);
        verify(produtoInputMapper).paraProdutoDTO(produtoPersistido);
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
    void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido(ProdutoRespostaDTO produtoRespostaDTO) {
        Long id = produtoRespostaDTO.getId();

        ResponseEntity<Void> response = produtoControlador.deletaProduto(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(produtoDeletaUseCase).deleta(id);
    }

    @ParameterizedTest
    @MethodSource("produtoFiltroProvider")
    @DisplayName("Dado produtos existentes, quando buscar o produto através do filtro, então ele deve ser retornado")
    void dadoProdutosExistentes_quandoBuscarProdutoAtravesDoFiltro_entaoDeveSerRetornado(
            ProdutoFiltroDTO filtroDTO,
            ProdutoFiltro filtro,
            List<Produto> produtos,
            List<ProdutoRespostaDTO> produtoRespostaDTOs) {

        Pageable paginacao = PageRequest.of(0, 10);
        Page<Produto> produtoPage = new PageImpl<>(produtos, paginacao, produtos.size());
        Page<ProdutoRespostaDTO> produtoRespostaPage =
                new PageImpl<>(produtoRespostaDTOs, paginacao, produtoRespostaDTOs.size());

        when(produtoFiltroInputMapper.paraProdutoFiltro(filtroDTO)).thenReturn(filtro);
        when(produtoListaUseCase.listarComFiltro(filtro, paginacao)).thenReturn(produtoPage);
        produtos.forEach(produto -> when(produtoInputMapper.paraProdutoDTO(produto))
                .thenReturn(produtoRespostaDTOs.get(produtos.indexOf(produto))));

        ResponseEntity<PagedModel<ProdutoRespostaDTO>> response =
                produtoControlador.listarProdutosComFiltro(filtroDTO, paginacao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoRespostaPage.getContent(), response.getBody().getContent());
        verify(produtoListaUseCase).listarComFiltro(filtro, paginacao);
        verify(produtoFiltroInputMapper).paraProdutoFiltro(filtroDTO);
        produtos.forEach(produto -> verify(produtoInputMapper).paraProdutoDTO(produto));
    }
}
