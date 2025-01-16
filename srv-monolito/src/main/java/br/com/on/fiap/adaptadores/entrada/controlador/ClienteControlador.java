package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.portas.entrada.cliente.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteControlador implements ClienteControladorSwagger {

	private final InsereClientePortaEntrada insereClientePortaEntrada;
	private final ClienteEntradaMapeador clienteEntradaMapeador;
	private final BuscaClientePorCpfPortaEntrada buscaClientePorCpfPortaEntrada;

	public ClienteControlador(InsereClientePortaEntrada insereClientePortaEntrada,
			ClienteEntradaMapeador clienteEntradaMapeador,
			BuscaClientePorCpfPortaEntrada buscaClientePorCpfPortaEntrada) {
		this.insereClientePortaEntrada = insereClientePortaEntrada;
		this.clienteEntradaMapeador = clienteEntradaMapeador;
		this.buscaClientePorCpfPortaEntrada = buscaClientePorCpfPortaEntrada;
	}

	@Override
	@PostMapping
	public ResponseEntity<ClienteRespostaDTO> insereCliente(
			@Valid @RequestBody ClienteSolicitacaoDTO clienteSolicitacaoDTO) {
		Cliente cliente = clienteEntradaMapeador.paraCliente(clienteSolicitacaoDTO);
		Cliente clientePersistido = insereClientePortaEntrada.inserir(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntradaMapeador.paraClienteDTO(clientePersistido));
	}

	@Override
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteRespostaDTO> buscaClientePorCpf(@PathVariable("cpf") String cpf) {
		Cliente cliente = buscaClientePorCpfPortaEntrada.buscar(cpf);
		return ResponseEntity.ok().body(clienteEntradaMapeador.paraClienteDTO(cliente));
	}
}
