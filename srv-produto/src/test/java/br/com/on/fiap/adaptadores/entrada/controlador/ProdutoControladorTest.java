package br.com.on.fiap.adaptadores.entrada.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoEntradaMapeador;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProdutoControladorTest {

  @Mock private BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;

  @Mock private InsereProdutoPortaEntrada insereProdutoPortaEntrada;

  @Mock private AlteraProdutoPortaEntrada alteraProdutoPortaEntrada;

  @Mock private DeletaProdutoPortaEntrada deletaProdutoPortaEntrada;

  @Mock private ProdutoEntradaMapeador produtoEntradaMapeador;

  @InjectMocks private ProdutoControlador produtoControlador;

  static Stream<ProdutoDTO> produtoDTOProvider() {
    return Stream.of(
        new ProdutoDTO(1L, "Produto 1", "Descricao 1", BigDecimal.valueOf(10.0)),
        new ProdutoDTO(2L, "Produto 2", "Descricao 2", BigDecimal.valueOf(20.0)),
        new ProdutoDTO(3L, "Produto 3", "Descricao 3", BigDecimal.valueOf(30.0)));
  }

  @ParameterizedTest
  @MethodSource("produtoDTOProvider")
  @DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
  void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado(ProdutoDTO produtoDTO) {
    Long id = produtoDTO.id();
    Produto produto = new Produto();
    when(buscaProdutoPorIdPortaEntrada.buscar(id)).thenReturn(produto);
    when(produtoEntradaMapeador.paraProdutoDTO(produto)).thenReturn(produtoDTO);

    ResponseEntity<ProdutoDTO> response = produtoControlador.buscaProdutoPorId(id);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(produtoDTO, response.getBody());
    verify(buscaProdutoPorIdPortaEntrada).buscar(id);
    verify(produtoEntradaMapeador).paraProdutoDTO(produto);
  }

  @ParameterizedTest
  @MethodSource("produtoDTOProvider")
  @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
  void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo(ProdutoDTO produtoDTO) {
    Produto produto = new Produto();
    Produto produtoPersistido = new Produto();
    ProdutoDTO produtoPersistidoDTO =
        new ProdutoDTO(
            produtoDTO.id(), produtoDTO.nome(), produtoDTO.categoria(), produtoDTO.preco());
    when(produtoEntradaMapeador.paraProduto(produtoDTO)).thenReturn(produto);
    when(insereProdutoPortaEntrada.inserir(produto)).thenReturn(produtoPersistido);
    when(produtoEntradaMapeador.paraProdutoDTO(produtoPersistido)).thenReturn(produtoPersistidoDTO);

    ResponseEntity<ProdutoDTO> response = produtoControlador.insereProduto(produtoDTO);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(produtoPersistidoDTO, response.getBody());
    verify(produtoEntradaMapeador).paraProduto(produtoDTO);
    verify(insereProdutoPortaEntrada).inserir(produto);
    verify(produtoEntradaMapeador).paraProdutoDTO(produtoPersistido);
  }

  @ParameterizedTest
  @MethodSource("produtoDTOProvider")
  @DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
  void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado(ProdutoDTO produtoDTO) {
    Long id = produtoDTO.id();
    Produto produto = new Produto();
    Produto produtoPersistido = new Produto();
    ProdutoDTO produtoPersistidoDTO =
        new ProdutoDTO(
            produtoDTO.id(), produtoDTO.nome(), produtoDTO.categoria(), produtoDTO.preco());
    when(produtoEntradaMapeador.paraProduto(produtoDTO)).thenReturn(produto);
    when(alteraProdutoPortaEntrada.alterar(id, produto)).thenReturn(produtoPersistido);
    when(produtoEntradaMapeador.paraProdutoDTO(produtoPersistido)).thenReturn(produtoPersistidoDTO);

    ResponseEntity<ProdutoDTO> response = produtoControlador.alteraProduto(id, produtoDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(produtoPersistidoDTO, response.getBody());
    verify(produtoEntradaMapeador).paraProduto(produtoDTO);
    verify(alteraProdutoPortaEntrada).alterar(id, produto);
    verify(produtoEntradaMapeador).paraProdutoDTO(produtoPersistido);
  }

  @ParameterizedTest
  @MethodSource("produtoDTOProvider")
  @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
  void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido(ProdutoDTO produtoDTO) {
    Long id = produtoDTO.id();

    ResponseEntity<ProdutoDTO> response = produtoControlador.deletaProduto(id);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(deletaProdutoPortaEntrada).deleta(id);
  }
}
