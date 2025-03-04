package br.com.on.fiap.adapter.input.swagger;

import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Pagamentos", description = "APIs relacionadas a pagamentos")
public interface PagamentoApiSwagger {

    @Operation(summary = "Atualiza pagamento", description = "Atualiza pagamento no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Pagamento atualizado",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = PagamentoRespostaDTO.class))),
                @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<PagamentoRespostaDTO> atualizaPagamento(String nrProtocolo);
}
