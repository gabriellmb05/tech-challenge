package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class PedidoEntidade {

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
