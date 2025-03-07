package br.com.on.fiap.adapter.output.db.entity.converter;

import br.com.on.fiap.core.domain.Categoria;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter
public class TipoCategoriaConverter implements AttributeConverter<Categoria, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Categoria categoria) {
        return Objects.nonNull(categoria) ? categoria.getCodigo() : null;
    }

    @Override
    public Categoria convertToEntityAttribute(Integer categoria) {
        return Objects.nonNull(categoria) ? Categoria.deCodigo(categoria) : null;
    }
}
