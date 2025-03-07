package br.com.on.fiap.adapter.input.dto.request;

import br.com.on.fiap.core.application.dto.entrada.cliente.ClienteEntrada;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteSolicitacaoDTO implements ClienteEntrada {

    @NotNull(message = "O atributo nome é obrigatório") private String nome;

    @NotNull(message = "O atributo cpf é obrigatório") @CPF(message = "O CPF informado é inválido")
    private String cpf;

    @NotNull(message = "O atributo email é obrigatório") @Email(message = "O E-mail informado é inválido")
    private String email;

    private LocalDate dataNascimento;
}
