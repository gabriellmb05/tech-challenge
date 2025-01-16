package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PedidoSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pedido", description = "APIs relacionadas a pedidos")
public interface PedidoControladorSwagger {

	@Operation(summary = "Insere um novo pedido", description = "Insere um novo pedido no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pedido criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
	ResponseEntity<PedidoRespostaDTO> inserePedido(PedidoSolicitacaoDTO pedidoSolicitacaoDTO);

}
