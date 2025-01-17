package br.com.on.fiap.adaptadores.saida.persistencia.entidade.relacionamento;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RelPedId implements Serializable {

    private Long pedId;
    private Long proId;
}
