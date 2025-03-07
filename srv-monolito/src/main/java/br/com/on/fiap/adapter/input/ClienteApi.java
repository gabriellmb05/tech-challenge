package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.request.ClienteSolicitacaoDTO;
import br.com.on.fiap.adapter.input.swagger.ClienteApiSwagger;
import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.application.dto.resposta.ClienteRespostaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteApi implements ClienteApiSwagger {

    private final ClienteController clienteController;

    public ClienteApi(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteRespostaDTO> insereCliente(
            @Valid @RequestBody ClienteSolicitacaoDTO clienteSolicitacaoDTO) {
        ClienteRespostaDTO clienteRespostaDTO = clienteController.insereCliente(clienteSolicitacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRespostaDTO);
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(@PathVariable("cpf") String cpf) {
        ClienteRespostaDTO cliente = clienteController.buscaClientePorCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }
}
