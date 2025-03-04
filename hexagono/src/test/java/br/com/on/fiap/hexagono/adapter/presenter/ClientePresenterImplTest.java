package br.com.on.fiap.hexagono.adapter.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.adapter.presenter.impl.ClientePresenterImpl;
import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteSaidaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientePresenterImplTest {

    @InjectMocks
    private ClientePresenterImpl clientePresenter;

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteRespostaDTO resultado = clientePresenter.formatar(clienteSaidaDTO);

        assertNotNull(resultado);
        assertEquals(clienteSaidaDTO.getCpf(), resultado.getCpf());
        assertEquals(clienteSaidaDTO.getId(), resultado.getId());
        assertEquals(clienteSaidaDTO.getNome(), resultado.getNome());
        assertEquals(clienteSaidaDTO.getDataNascimento(), resultado.getDataNascimento());
        assertEquals(clienteSaidaDTO.getEmail(), resultado.getEmail());
    }
}
