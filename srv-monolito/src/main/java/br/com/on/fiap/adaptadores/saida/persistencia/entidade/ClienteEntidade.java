package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLI_CLIENTE")
public class ClienteEntidade {

    private static final String SQ_CLI_CLIENTE = "SQ_CLI_CLIENTE";

    @Id
    @SequenceGenerator(name = SQ_CLI_CLIENTE, sequenceName = SQ_CLI_CLIENTE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_CLI_CLIENTE)
    @Column(name = "CLI_ID")
    private Long id;

    @Column(name = "CLI_NM_NOME")
    private String nmNome;

    @Column(name = "CLI_NM_CPF")
    private String nmCpf;

    @Column(name = "CLI_NM_EMAIL")
    private String nmEmail;

    @Column(name = "CLI_DH_NASCIMENTO")
    private LocalDate dhNascimento;
}
