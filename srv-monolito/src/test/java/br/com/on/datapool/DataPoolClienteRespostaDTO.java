package br.com.on.datapool;

import br.com.on.fiap.core.application.dto.resposta.cliente.ClienteResposta;
import java.time.LocalDate;

public class DataPoolClienteRespostaDTO {

    private static ClienteResposta construirClienteRespostaDTO(
            Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
        return new ClienteResposta(id, nome, cpf, email, dataNascimento);
    }

    public static ClienteResposta gerarCliente() {
        return construirClienteRespostaDTO(1L, "43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
