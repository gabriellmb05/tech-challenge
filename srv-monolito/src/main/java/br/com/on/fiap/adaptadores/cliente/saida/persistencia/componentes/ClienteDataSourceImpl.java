package br.com.on.fiap.adaptadores.cliente.saida.persistencia.componentes;

import br.com.on.fiap.adaptadores.cliente.saida.persistencia.mapeador.ClienteSaidaMapeador;
import br.com.on.fiap.adaptadores.cliente.saida.persistencia.repositorio.ClienteRepositorio;
import br.com.on.fiap.entidades.ClienteEntidade;
import br.com.on.fiap.hexagono.adaptadores.datasource.ClienteDataSource;
import br.com.on.fiap.hexagono.entidades.Cliente;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataSourceImpl implements ClienteDataSource {

    private final ClienteRepositorio clienteRepositorio;
    private final ClienteSaidaMapeador clienteSaidaMapeador;

    public ClienteDataSourceImpl(ClienteRepositorio clienteRepositorio, ClienteSaidaMapeador clienteSaidaMapeador) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteSaidaMapeador = clienteSaidaMapeador;
    }

    @Override
    public Optional<Cliente> findByNmCpf(String cpf) {
        return clienteRepositorio.findByNmCpf(cpf).map(clienteSaidaMapeador::paraCliente);
    }

    @Override
    public Optional<Cliente> findByNmEmail(String email) {
        return clienteRepositorio.findByNmEmail(email).map(clienteSaidaMapeador::paraCliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepositorio.findById(id).map(clienteSaidaMapeador::paraCliente);
    }

    @Override
    public Cliente persist(Cliente cliente) {
        ClienteEntidade clienteEntidade = clienteSaidaMapeador.paraEntidade(cliente);
        ClienteEntidade clienteEntidadeSalva = clienteRepositorio.save(clienteEntidade);
        return clienteSaidaMapeador.paraCliente(clienteEntidadeSalva);
    }
}
