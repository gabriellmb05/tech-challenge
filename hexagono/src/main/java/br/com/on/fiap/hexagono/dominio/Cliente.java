package br.com.on.fiap.hexagono.dominio;

import java.time.LocalDate;

public class Cliente {
	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private LocalDate dataNascimento;

	public Cliente() {
	}

	public Cliente(Long id, String cpf, String nome, String email, LocalDate dataNascimento) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
