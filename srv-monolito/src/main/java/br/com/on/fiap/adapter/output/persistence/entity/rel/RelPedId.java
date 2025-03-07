package br.com.on.fiap.adapter.output.persistence.entity.rel;

import jakarta.persistence.Column;
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

    @Column(name = "PED_ID")
    private Long pedId;

    @Column(name = "PRO_ID")
    private Long proId;

    public static RelPedId create(Long pedId, Long proId) {
        return new RelPedId(pedId, proId);
    }
}
