package br.com.on.fiap.hexagono.interfaceadapters.apresentadores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.datapool.DataPoolClienteSaidaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.ClienteApresentador;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteApresentadorImplTest {

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteApresentador clienteApresentador = new ClienteApresentadorImpl();

        ClienteRespostaDTO resultado = clienteApresentador.formatar(clienteSaidaDTO);

        assertNotNull(resultado);
        assertEquals(clienteSaidaDTO.getCpf(), resultado.getCpf());
        assertEquals(clienteSaidaDTO.getId(), resultado.getId());
        assertEquals(clienteSaidaDTO.getNome(), resultado.getNome());
        assertEquals(clienteSaidaDTO.getDataNascimento(), resultado.getDataNascimento());
        assertEquals(clienteSaidaDTO.getEmail(), resultado.getEmail());
    }
}
