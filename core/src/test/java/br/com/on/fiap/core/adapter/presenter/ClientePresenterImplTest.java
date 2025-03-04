package br.com.on.fiap.core.adapter.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.adapter.presenter.impl.ClientePresenterImpl;
import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.datapool.DataPoolCliente;
import br.com.on.fiap.core.domain.entity.Cliente;
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
        Cliente cliente = DataPoolCliente.clienteValido();
        ClienteRespostaDTO resultado = clientePresenter.formatar(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getCpf(), resultado.getCpf());
        assertEquals(cliente.getId(), resultado.getId());
        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getDataNascimento(), resultado.getDataNascimento());
        assertEquals(cliente.getEmail(), resultado.getEmail());
    }
}
