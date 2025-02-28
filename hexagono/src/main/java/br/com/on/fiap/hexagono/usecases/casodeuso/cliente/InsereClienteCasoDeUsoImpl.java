package br.com.on.fiap.hexagono.usecases.casodeuso.cliente;

import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.usecases.excecao.ClienteExistenteExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import br.com.on.fiap.hexagono.usecases.utilitarios.FormatadorCpf;

public class InsereClienteCasoDeUsoImpl implements InsereClienteCasoDeUso {

    private final ClienteGateway clienteGateway;

    public InsereClienteCasoDeUsoImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteSaidaDTO inserir(ClienteEntradaDTO clienteEntradaDTO) {
        Cliente cliente = new Cliente(
                clienteEntradaDTO.getCpf(),
                clienteEntradaDTO.getNome(),
                clienteEntradaDTO.getEmail(),
                clienteEntradaDTO.getDataNascimento());
        clienteGateway.buscaClientePorCpf(cliente.getCpf()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_CPF_JA_CADASTRADO.getMensagem(),
                    FormatadorCpf.formatarCpf(cliente.getCpf()));
        });

        clienteGateway.buscaClientePorEmail(cliente.getEmail()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_EMAIL_JA_CADASTRADO.getMensagem(), cliente.getEmail());
        });
        Cliente clientePersistido = clienteGateway.salvaCliente(cliente);
        return new ClienteSaidaDTO(
                clientePersistido.getId(),
                clientePersistido.getNome(),
                clientePersistido.getCpf(),
                clientePersistido.getEmail(),
                clientePersistido.getDataNascimento());
    }
}
