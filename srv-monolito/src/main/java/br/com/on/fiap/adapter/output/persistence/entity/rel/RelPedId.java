package br.com.on.fiap.adapter.output.persistence.entity.rel;

import jakarta.persistence.*;
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
}
