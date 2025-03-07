package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.entrada.ClienteRequest;
import br.com.on.fiap.adapter.input.swagger.ClienteApiSwagger;
import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;
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
    public ResponseEntity<ClienteResposta> insereCliente(
            @Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResposta clienteResposta = clienteController.insereCliente(clienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResposta);
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResposta> buscaClientePorCpf(@PathVariable("cpf") String cpf) {
        ClienteResposta cliente = clienteController.buscaClientePorCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }
}
