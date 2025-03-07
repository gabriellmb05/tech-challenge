package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.on.datapool.*;
import br.com.on.fiap.adapter.input.dto.filter.ProdutoFiltroRequest;
import br.com.on.fiap.adapter.input.dto.request.ProdutoSolicitacaoRequest;
import br.com.on.fiap.adapter.input.dto.response.PaginaInfo;
import br.com.on.fiap.adapter.input.dto.response.PaginacaoRespostaInfo;
import br.com.on.fiap.core.adapter.controller.impl.ProdutoControllerImpl;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProdutoApiTest {

    @Mock
    private ProdutoControllerImpl produtoController;

    @InjectMocks
    private ProdutoApi produtoApi;

    static Stream<Arguments> produtoDTOProvider() {
        return Stream.of(
                Arguments.of(DataPoolProdutoRespostaDTO.gerarProduto1(), DataPoolProdutoSolicitacaoDTO.gerarProduto1()),
                Arguments.of(DataPoolProdutoRespostaDTO.gerarProduto2(), DataPoolProdutoSolicitacaoDTO.gerarProduto2()),
                Arguments.of(
                        DataPoolProdutoRespostaDTO.gerarProduto3(), DataPoolProdutoSolicitacaoDTO.gerarProduto3()));
    }

    static Stream<Arguments> produtoFiltroProvider() {
        return Stream.of(
                Arguments.of(DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(), Collections.emptyList()),
                Arguments.of(
                        DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(),
                        List.of(DataPoolProdutoRespostaDTO.gerarProdutoXBurguer())),
                Arguments.of(
                        DataPoolProdutoFiltroDTO.gerarProdutoXBurguer(),
                        DataPoolProdutoRespostaDTO.gerarListaProdutoRespostaDTO()));
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
    void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado(ProdutoResposta produtoResposta) {
        Long id = produtoResposta.getId();
        when(produtoController.buscaProdutoPorId(id)).thenReturn(produtoResposta);

        ResponseEntity<ProdutoResposta> response = produtoApi.buscaProdutoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoResposta, response.getBody());
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
    void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo(
            ProdutoResposta produtoResposta, ProdutoSolicitacaoRequest produtoSolicitacaoRequestDTO) {
        when(produtoController.insereProduto(produtoSolicitacaoRequestDTO)).thenReturn(produtoResposta);
        ResponseEntity<ProdutoResposta> response = produtoApi.insereProduto(produtoSolicitacaoRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produtoResposta, response.getBody());
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
    void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado(
            ProdutoResposta produtoRespotaDTO, ProdutoSolicitacaoRequest produtoSolicitacaoRequestDTO) {
        Long id = produtoRespotaDTO.getId();
        when(produtoController.alteraProduto(id, produtoSolicitacaoRequestDTO)).thenReturn(produtoRespotaDTO);
        ResponseEntity<ProdutoResposta> response = produtoApi.alteraProduto(id, produtoSolicitacaoRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produtoRespotaDTO, response.getBody());
    }

    @ParameterizedTest
    @MethodSource("produtoDTOProvider")
    @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
    void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido(ProdutoResposta produtoResposta) {
        Long id = produtoResposta.getId();
        ResponseEntity<Void> response = produtoApi.deletaProduto(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @ParameterizedTest
    @MethodSource("produtoFiltroProvider")
    @DisplayName("Dado produtos existentes, quando buscar o produto através do filtro, então ele deve ser retornado")
    void dadoProdutosExistentes_quandoBuscarProdutoAtravesDoFiltro_entaoDeveSerRetornado(
            ProdutoFiltroRequest filtroDTO, List<ProdutoResposta> produtoRespostas) {

        PageRequest pageable = PageRequest.of(0, 10);
        PaginacaoRespostaInfo paginacao = PaginacaoRespostaInfo.from(pageable);

        Pagina<ProdutoResposta> pageProduto =
                PaginaInfo.<ProdutoResposta>builder().conteudo(produtoRespostas).build();

        when(produtoController.listarProdutosComFiltro(filtroDTO, paginacao)).thenReturn(pageProduto);

        ResponseEntity<Pagina<ProdutoResposta>> response = produtoApi.listarProdutosComFiltro(filtroDTO, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pageProduto.getConteudo(), response.getBody().getConteudo());
    }
}
