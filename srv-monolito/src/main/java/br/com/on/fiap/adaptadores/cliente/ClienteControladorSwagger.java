package br.com.on.fiap.adaptadores.cliente;

import br.com.on.fiap.adaptadores.cliente.entrada.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.domain.entity.Cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cliente", description = "APIs relacionadas a clientes")
public interface ClienteControladorSwagger {

    @Operation(summary = "Insere um novo cliente", description = "Insere um novo cliente no sistema")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Cliente criado",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Cliente.class))),
                @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<ClienteRespostaDTO> insereCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);

    @Operation(summary = "Busca cliente por CPF", description = "Retorna um cliente pelo seu CPF")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Cliente encontrado",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Cliente.class))),
                @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
                @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(String cpf);
}
