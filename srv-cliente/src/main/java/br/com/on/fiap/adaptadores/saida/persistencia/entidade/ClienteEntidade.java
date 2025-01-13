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
@Table(name = "clientes")
public class ClienteEntidade {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_id_seq")
  @SequenceGenerator(name = "clientes_id_seq", sequenceName = "clientes_id_seq", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  private String nome;

  private String cpf;

  private String email;

  private LocalDate dataNascimento;
}
