package br.com.on.fiap.hexagono.adaptadores.apresentadores.impl;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.ClienteApresentador;
import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;

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
