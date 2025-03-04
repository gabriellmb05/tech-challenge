package br.com.on.fiap.hexagono.adapter.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.base.ClienteBasePresenter;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteSaidaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientePresenterTest {

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteBasePresenter clientePresenter = new ClientePresenter();

        ClienteRespostaDTO resultado = clientePresenter.formatar(clienteSaidaDTO);

        assertNotNull(resultado);
        assertEquals(clienteSaidaDTO.getCpf(), resultado.getCpf());
        assertEquals(clienteSaidaDTO.getId(), resultado.getId());
        assertEquals(clienteSaidaDTO.getNome(), resultado.getNome());
        assertEquals(clienteSaidaDTO.getDataNascimento(), resultado.getDataNascimento());
        assertEquals(clienteSaidaDTO.getEmail(), resultado.getEmail());
    }
}
