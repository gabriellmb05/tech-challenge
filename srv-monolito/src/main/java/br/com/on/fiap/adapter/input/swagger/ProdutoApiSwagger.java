package br.com.on.fiap.adapter.input.swagger;

import br.com.on.fiap.adapter.input.dto.entrada.ProdutoRequest;
import br.com.on.fiap.adapter.input.dto.filtro.ProdutoFiltroRequest;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Produto", description = "APIs relacionadas a produtos")
public interface ProdutoApiSwagger {

    @Operation(summary = "Busca produto por ID", description = "Retorna um produto pelo seu ID")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Produto encontrado"),
                @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            })
    ResponseEntity<ProdutoResposta> buscaProdutoPorId(Long id);

    @Operation(
            summary = "Busca produtos",
            description = "Retorna uma lista de produtos de forma paginada e permite filtrar por categoria e/ou nome")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
                @ApiResponse(responseCode = "404", description = "Produtos não encontrados")
            })
    ResponseEntity<PaginaResposta<ProdutoResposta>> listarProdutosComFiltro(
            ProdutoFiltroRequest filtro, Pageable pageable);

    @Operation(summary = "Insere um novo produto", description = "Insere um novo produto no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Produto criado"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos")
            })
    ResponseEntity<ProdutoResposta> insereProduto(ProdutoRequest produtoRequestDTO);

    @Operation(summary = "Altera um produto", description = "Altera um produto existente no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Produto alterado"),
                @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            })
    ResponseEntity<ProdutoResposta> alteraProduto(Long id, ProdutoRequest produtoRequestDTO);

    @Operation(summary = "Deleta um produto", description = "Deleta um produto existente no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Produto deletado"),
                @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            })
    ResponseEntity<Void> deletaProduto(Long id);
}
