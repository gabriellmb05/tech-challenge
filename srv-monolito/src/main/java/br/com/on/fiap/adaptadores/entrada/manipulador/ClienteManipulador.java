package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.manipulador.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.manipulador.swagger.ClienteControladorSwagger;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.ClienteControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteManipulador implements ClienteControladorSwagger {

    private final ClienteEntradaMapeador clienteEntradaMapeador;
    private final ClienteControlador clienteControlador;

    public ClienteManipulador(ClienteEntradaMapeador clienteEntradaMapeador, ClienteControlador clienteControlador) {
        this.clienteEntradaMapeador = clienteEntradaMapeador;
        this.clienteControlador = clienteControlador;
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteRespostaDTO> insereCliente(
            @Valid @RequestBody ClienteSolicitacaoDTO clienteSolicitacaoDTO) {
        ClienteEntradaDTO clienteEntradaDTO = clienteEntradaMapeador.paraClienteDTO(clienteSolicitacaoDTO);
        ClienteRespostaDTO clienteRespostaDTO = clienteControlador.insereCliente(clienteEntradaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRespostaDTO);
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(@PathVariable("cpf") String cpf) {
        ClienteRespostaDTO cliente = clienteControlador.buscaClientePorCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }
}
