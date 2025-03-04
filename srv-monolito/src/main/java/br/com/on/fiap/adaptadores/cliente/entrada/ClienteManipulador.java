package br.com.on.fiap.adaptadores.cliente.entrada;

import br.com.on.fiap.adaptadores.cliente.ClienteControladorSwagger;
import br.com.on.fiap.adaptadores.cliente.entrada.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.adaptadores.cliente.entrada.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.hexagono.adapter.controller.base.ClienteController;
import br.com.on.fiap.hexagono.adapter.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteManipulador implements ClienteControladorSwagger {

    private final ClienteEntradaMapeador clienteEntradaMapeador;
    private final ClienteController clienteController;

    public ClienteManipulador(ClienteEntradaMapeador clienteEntradaMapeador, ClienteController clienteController) {
        this.clienteEntradaMapeador = clienteEntradaMapeador;
        this.clienteController = clienteController;
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteRespostaDTO> insereCliente(
            @Valid @RequestBody ClienteSolicitacaoDTO clienteSolicitacaoDTO) {
        ClienteEntradaDTO clienteEntradaDTO = clienteEntradaMapeador.paraClienteDTO(clienteSolicitacaoDTO);
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
