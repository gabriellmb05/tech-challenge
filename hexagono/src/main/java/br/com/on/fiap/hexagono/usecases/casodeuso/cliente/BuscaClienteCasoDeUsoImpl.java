package br.com.on.fiap.hexagono.usecases.casodeuso.cliente;

import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.usecases.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import br.com.on.fiap.hexagono.usecases.utilitarios.FormatadorCpf;

public class BuscaClienteCasoDeUsoImpl implements BuscaClientePorCpfCasoDeUso {

    private final ClienteGateway clienteGateway;

    public BuscaClienteCasoDeUsoImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteSaidaDTO buscar(String cpf) {
        Cliente cliente = clienteGateway
                .buscaClientePorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_CLIENTE_NAO_CADASTRADO.getMensagem(), FormatadorCpf.formatarCpf(cpf)));
        return new ClienteSaidaDTO(
                cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataNascimento());
    }
}
