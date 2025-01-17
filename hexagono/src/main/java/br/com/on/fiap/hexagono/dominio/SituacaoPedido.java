package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.stream.Stream;

public enum SituacaoPedido {
	PENDENTE(1), APROVADO(2), CANCELADO(3), ENTREGUE(4);

	private final Integer codigo;

	SituacaoPedido(Integer codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static SituacaoPedido fromCodigo(int codigo) {
		for (SituacaoPedido situacao : SituacaoPedido.values()) {
			if (situacao.getCodigo() == codigo) {
				return situacao;
			}
		}
		throw new IllegalArgumentException("Código de situação inválido: " + codigo);
	}

	public static SituacaoPedido deCodigo(Integer codigo) {
		return Stream.of(SituacaoPedido.values()).filter(situacaoPedido -> situacaoPedido.getCodigo() == codigo)
				.findFirst().orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
						MessageError.MSG_ERRO_CATEGORIA_NAO_CADASTRADA.getMensagem(), codigo));
	}
}
