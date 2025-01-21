package br.com.on.fiap.datapool;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ClienteRespostaDTO;
import java.time.LocalDate;

public class DataPoolClienteRespostaDTO {

    private static ClienteRespostaDTO construirClienteRespostaDTO(
            Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
        return ClienteRespostaDTO.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .dataNascimento(dataNascimento)
                .build();
    }

    public static ClienteRespostaDTO gerarCliente() {
        return construirClienteRespostaDTO(1L, "43316652616", "Elmo Cameron", "lectus@yahoo.edu", LocalDate.now());
    }
}
