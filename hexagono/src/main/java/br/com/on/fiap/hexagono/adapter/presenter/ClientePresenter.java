package br.com.on.fiap.hexagono.adapter.presenter;

import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteSaidaDTO;

public class ClientePresenter implements br.com.on.fiap.hexagono.adapter.presenter.base.ClienteBasePresenter {

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
