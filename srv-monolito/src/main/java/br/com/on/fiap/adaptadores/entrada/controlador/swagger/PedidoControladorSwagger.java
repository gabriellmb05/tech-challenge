package br.com.on.fiap.adaptadores.entrada.controlador.swagger;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pedido", description = "APIs relacionadas a pedidos")
public interface PedidoControladorSwagger {

	@Operation(summary = "Insere um novo pedido", description = "Insere um novo pedido no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pedido criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
	ResponseEntity<PedidoRespostaDTO> inserePedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

	@Operation(summary = "Lista pedidos", description = "Retorna uma lista de pedidos de forma paginada")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
			@ApiResponse(responseCode = "404", description = "Pedidos não encontrados")})
	ResponseEntity<PagedModel<PedidoRespostaDTO>> buscaPedidosPaginado(PedidoFiltroDTO pedidoFiltroDTO,
			Pageable pageable);

	@Operation(summary = "Detalha um pedido", description = "Retorna os detalhes de um pedido específico")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pedido encontrado"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado")})
	ResponseEntity<PedidoDetalhadoRespostaDTO> detalhaPedido(Long id);
}
