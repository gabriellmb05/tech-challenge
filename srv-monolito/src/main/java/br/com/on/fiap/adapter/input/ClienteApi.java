package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.dto.request.ClienteSolicitacaoDTO;
import br.com.on.fiap.adapter.input.mapper.ClienteInputMapper;
import br.com.on.fiap.adapter.input.swagger.ClienteApiSwagger;
import br.com.on.fiap.hexagono.adapter.controller.ClienteController;
import br.com.on.fiap.hexagono.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteApi implements ClienteApiSwagger {

    private final ClienteInputMapper clienteInputMapper;
    private final ClienteController clienteController;

    public ClienteApi(ClienteInputMapper clienteInputMapper, ClienteController clienteController) {
        this.clienteInputMapper = clienteInputMapper;
        this.clienteController = clienteController;
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteRespostaDTO> insereCliente(
            @Valid @RequestBody ClienteSolicitacaoDTO clienteSolicitacaoDTO) {
        ClienteEntradaDTO clienteEntradaDTO = clienteInputMapper.paraClienteDTO(clienteSolicitacaoDTO);
        ClienteRespostaDTO clienteRespostaDTO = clienteController.insereCliente(clienteEntradaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRespostaDTO);
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(@PathVariable("cpf") String cpf) {
        ClienteRespostaDTO cliente = clienteController.buscaClientePorCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }
}
