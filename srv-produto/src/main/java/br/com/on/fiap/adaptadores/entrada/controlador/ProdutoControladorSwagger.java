package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoSolicitacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produto", description = "APIs relacionadas a produtos")
public interface ProdutoControladorSwagger {

  @Operation(summary = "Busca produto por ID", description = "Retorna um produto pelo seu ID")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
      })
  @GetMapping("/{id}")
  ResponseEntity<ProdutoRespostaDTO> buscaProdutoPorId(Long id);

  @Operation(summary = "Busca produtos", description = "Retorna uma lista de produtos de forma paginada e permite filtrar por categoria")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Produtos não encontrados")
            })
    @GetMapping
    public ResponseEntity<Page<ProdutoRespostaDTO>> listarProdutosPorCategoria( String categoria, int page, int size);

  @Operation(summary = "Insere um novo produto", description = "Insere um novo produto no sistema")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "Produto criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
      })
  @PostMapping
  ResponseEntity<ProdutoRespostaDTO> insereProduto(ProdutoSolicitacaoDTO produtoSolicitacaoDTO);

  @Operation(summary = "Altera um produto", description = "Altera um produto existente no sistema")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produto alterado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
      })
  @PutMapping("/{id}")
  ResponseEntity<ProdutoRespostaDTO> alteraProduto(
      Long id, ProdutoSolicitacaoDTO produtoSolicitacaoDTO);

  @Operation(summary = "Deleta um produto", description = "Deleta um produto existente no sistema")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Produto deletado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
      })
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deletaProduto(Long id);
}
