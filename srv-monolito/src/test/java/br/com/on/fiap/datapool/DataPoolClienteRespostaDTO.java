package br.com.on.fiap.datapool;

import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import java.time.LocalDate;

public class DataPoolClienteRespostaDTO {

    private static ClienteRespostaDTO construirClienteRespostaDTO(
            Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
        return new ClienteRespostaDTO(id, nome, cpf, email, dataNascimento);
    }

    public static ClienteRespostaDTO gerarCliente() {
        return construirClienteRespostaDTO(1L, "43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
