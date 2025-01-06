package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
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

	public ClienteEntidade() {

	}

	public ClienteEntidade(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
