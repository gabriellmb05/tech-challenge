package br.com.on.fiap.adaptadores.saida.persistencia.entidade;

import br.com.on.fiap.hexagono.dominio.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRO_PRODUTO")
public class ProdutoEntidade {

    private final static String SQ_PRO_PRODUTO = "SQ_PRO_PRODUTO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PRO_PRODUTO)
    @SequenceGenerator(name = SQ_PRO_PRODUTO, sequenceName = SQ_PRO_PRODUTO)
    @Column(name = "PRO_ID")
    private Long id;

    @Column(name = "PRO_NM_NOME")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRO_TP_CATEGORIA")
    private Categoria categoria;

    @Column(name = "PRO_VL_PRECO")
    private BigDecimal preco;
}
