package br.com.on.fiap.adapter.input.swagger;

import br.com.on.fiap.adapter.input.dto.filter.PedidoFiltroRequest;
import br.com.on.fiap.adapter.input.dto.request.PedidoSolicitacaoDTO;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.PedidoDetalhadoResposta;
import br.com.on.fiap.core.domain.model.PedidoResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Pedido", description = "APIs relacionadas a pedidos")
public interface PedidoApiSwagger {

    @Operation(summary = "Insere um novo pedido", description = "Insere um novo pedido no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Pedido criado",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = PedidoResposta.class))),
                @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<PedidoResposta> inserePedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

    @Operation(summary = "Lista pedidos", description = "Retorna uma lista de pedidos de forma paginada")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
                @ApiResponse(responseCode = "404", description = "Pedidos não encontrados")
            })
    ResponseEntity<Pagina<PedidoResposta>> listarPedidoComFiltro(
            PedidoFiltroRequest pedidoFiltroRequest, Pageable pageable);

    @Operation(summary = "Detalha um pedido", description = "Retorna os detalhes de um pedido específico")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
                @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    ResponseEntity<PedidoDetalhadoResposta> detalhaPedido(String protocolo);

    @Operation(
            summary = "Atualiza um pedido",
            description = "Atualiza as informações de um pedido existente, identificado pelo protocolo fornecido")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Pedido atualizado com sucesso",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = PedidoResposta.class))),
                @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização"),
                @ApiResponse(responseCode = "404", description = "Pedido não encontrado com o protocolo informado"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<PedidoResposta> atualizarPedido(@PathVariable("protocolo") String protocolo);
}
