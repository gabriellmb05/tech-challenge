package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;

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
