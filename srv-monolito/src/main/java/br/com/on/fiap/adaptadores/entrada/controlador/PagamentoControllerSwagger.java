package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.PagamentoSolicitacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pagamento", description = "APIs relacionadas a pagamentos")
public interface PagamentoControllerSwagger {

	@Operation(summary = "Cria um novo pagamento", description = "Cria um novo pagamento no sistema")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pagamento criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
	ResponseEntity<PagamentoRespostaDTO> criarPagamento(PagamentoSolicitacaoDTO pagamentoSolicitacaoDTO);
}
