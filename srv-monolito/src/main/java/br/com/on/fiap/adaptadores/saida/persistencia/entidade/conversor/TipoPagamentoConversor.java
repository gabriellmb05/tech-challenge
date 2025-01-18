package br.com.on.fiap.adaptadores.saida.persistencia.entidade.conversor;

import br.com.on.fiap.hexagono.dominio.TipoPagamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter
public class TipoPagamentoConversor implements AttributeConverter<TipoPagamento, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TipoPagamento tipoPagamento) {
        return Objects.nonNull(tipoPagamento) ? tipoPagamento.getCodigo() : null;
    }

    @Override
    public TipoPagamento convertToEntityAttribute(Integer tipoPagamento) {
        return Objects.nonNull(tipoPagamento) ? TipoPagamento.deCodigo(tipoPagamento) : null;
    }
}
