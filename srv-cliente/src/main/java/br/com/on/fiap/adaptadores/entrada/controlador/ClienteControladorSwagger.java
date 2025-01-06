package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteSolicitacaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Cliente", description = "APIs relacionadas a clientes")
public interface ClienteControladorSwagger {

	@Operation(summary = "Insere um novo cliente", description = "Insere um novo cliente no sistema")
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cliente criado"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos")})
	@PostMapping
	ResponseEntity<ClienteRespostaDTO> insereCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);

	@Operation(summary = "Busca cliente por CPF", description = "Retorna um cliente pelo seu CPF")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente encontrado"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado")})
	@GetMapping("/{cpf}")
	ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(String cpf);
}
