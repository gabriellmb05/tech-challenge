package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ClienteEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ClienteSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ClienteRepositorio;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaClienteAdaptador implements PersisteClientePortaSaida {

	private final ClienteRepositorio clienteRepositorio;
	private final ClienteSaidaMapeador clienteSaidaMapeador;

	public PersistenciaClienteAdaptador(ClienteRepositorio produtoRepositorio,
			ClienteSaidaMapeador produtoSaidaMapeador, ClienteRepositorio clienteRepositorio,
			ClienteSaidaMapeador clienteSaidaMapeador) {
		this.clienteRepositorio = clienteRepositorio;
		this.clienteSaidaMapeador = clienteSaidaMapeador;
	}

	@Override
	public Optional<Cliente> buscaClientePorCpf(String cpf) {
		return clienteRepositorio.findByCpf(cpf).map(clienteSaidaMapeador::paraCliente);
	}

	@Override
	public Cliente salvaCliente(Cliente cliente) {
		ClienteEntidade clienteEntidade = clienteSaidaMapeador.paraEntidade(cliente);
		ClienteEntidade clientePersistido = clienteRepositorio.save(clienteEntidade);
		return clienteSaidaMapeador.paraCliente(clientePersistido);
	}
}
