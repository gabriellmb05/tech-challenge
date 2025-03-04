package br.com.on.fiap.hexagono.casodeuso.cliente;

import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Cliente;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.utilitarios.FormatadorCpf;

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
