package br.com.on.fiap.hexagono.interfaceadapters.apresentadores;

import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.ClienteApresentador;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;

public class ClienteApresentadorImpl implements ClienteApresentador {

    @Override
    public ClienteRespostaDTO formatar(ClienteSaidaDTO clienteSaidaDTO) {
        return new ClienteRespostaDTO(
                clienteSaidaDTO.getId(),
                clienteSaidaDTO.getNome(),
                clienteSaidaDTO.getCpf(),
                clienteSaidaDTO.getEmail(),
                clienteSaidaDTO.getDataNascimento());
    }
}
