package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRespostaDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
}
