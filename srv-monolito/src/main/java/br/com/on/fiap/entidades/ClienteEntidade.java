package br.com.on.fiap.entidades;

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
    @Column(name = "CLI_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_CLI_CLIENTE)
    @SequenceGenerator(name = SQ_CLI_CLIENTE, sequenceName = SQ_CLI_CLIENTE, allocationSize = 1)
    private Long cliId;

    @Column(name = "CLI_NM_NOME")
    private String nmNome;

    @Column(name = "CLI_NM_CPF")
    private String nmCpf;

    @Column(name = "CLI_NM_EMAIL")
    private String nmEmail;

    @Column(name = "CLI_DH_NASCIMENTO")
    private LocalDate dhNascimento;
}
