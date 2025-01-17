package br.com.on.fiap.adaptadores.saida.persistencia.entidade.conversor;

import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter
public class SituacaoPedidoConversor implements AttributeConverter<SituacaoPedido, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SituacaoPedido situacaoPedido) {
        return Objects.nonNull(situacaoPedido) ? situacaoPedido.getCodigo() : null;
    }

    @Override
    public SituacaoPedido convertToEntityAttribute(Integer situacaoPedido) {
        return Objects.nonNull(situacaoPedido) ? SituacaoPedido.deCodigo(situacaoPedido) : null;
    }
}
