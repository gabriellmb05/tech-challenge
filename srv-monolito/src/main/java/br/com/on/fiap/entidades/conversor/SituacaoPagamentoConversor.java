package br.com.on.fiap.entidades.conversor;

import br.com.on.fiap.hexagono.entidades.SituacaoPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter
public class SituacaoPagamentoConversor implements AttributeConverter<SituacaoPagamento, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SituacaoPagamento situacaoPagamento) {
        return Objects.nonNull(situacaoPagamento) ? situacaoPagamento.getCodigo() : null;
    }

    @Override
    public SituacaoPagamento convertToEntityAttribute(Integer situacaoPagamento) {
        return Objects.nonNull(situacaoPagamento) ? SituacaoPagamento.deCodigo(situacaoPagamento) : null;
    }
}
