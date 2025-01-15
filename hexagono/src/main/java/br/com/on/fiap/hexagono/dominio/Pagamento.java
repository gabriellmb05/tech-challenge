package br.com.on.fiap.hexagono.dominio;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pagamento {

	private UUID idTransacao;
	private TipoPagamento tipo;
	private LocalDateTime dataHoraPagamento;

	public Pagamento() {
	}

	public Pagamento(UUID idTransacao, TipoPagamento tipo, LocalDateTime dataHoraPagamento) {
		this.idTransacao = idTransacao;
		this.tipo = tipo;
		this.dataHoraPagamento = dataHoraPagamento;
	}

	public UUID getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(UUID idTransacao) {
		this.idTransacao = idTransacao;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getDataHoraPagamento() {
		return dataHoraPagamento;
	}

	public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
		this.dataHoraPagamento = dataHoraPagamento;
	}
}
