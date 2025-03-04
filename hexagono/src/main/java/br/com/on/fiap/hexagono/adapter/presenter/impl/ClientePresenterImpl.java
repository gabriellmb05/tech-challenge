package br.com.on.fiap.hexagono.adapter.presenter.impl;

import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.ClientePresenter;

public class ClientePresenterImpl implements ClientePresenter {

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
