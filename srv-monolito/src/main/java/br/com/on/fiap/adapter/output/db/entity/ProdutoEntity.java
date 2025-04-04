package br.com.on.fiap.adapter.output.db.entity;

import br.com.on.fiap.adapter.output.db.entity.converter.TipoCategoriaConverter;
import br.com.on.fiap.core.domain.Categoria;
import br.com.on.fiap.core.domain.Produto;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRO_PRODUTO")
public class ProdutoEntity {

    private static final String SQ_PRO_PRODUTO = "SQ_PRO_PRODUTO";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SQ_PRO_PRODUTO)
    @SequenceGenerator(name = SQ_PRO_PRODUTO, sequenceName = SQ_PRO_PRODUTO, allocationSize = 1)
    @Column(name = "PRO_ID")
    private Long proId;

    @Column(name = "PRO_NM_NOME")
    private String nmNome;

    @Column(name = "PRO_TP_CATEGORIA")
    @Convert(converter = TipoCategoriaConverter.class)
    private Categoria tpCategoria;

    @Column(name = "PRO_VL_PRODUTO")
    private BigDecimal vlProduto;

    public static ProdutoEntity fromDomain(Produto produto) {
        return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPreco());
    }

    public Produto toDomain() {
        return new Produto(proId, nmNome, tpCategoria, vlProduto);
    }
}
