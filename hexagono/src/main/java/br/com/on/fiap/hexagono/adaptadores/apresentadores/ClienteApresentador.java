package br.com.on.fiap.hexagono.adaptadores.apresentadores;

import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;

public interface ClienteApresentador {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
