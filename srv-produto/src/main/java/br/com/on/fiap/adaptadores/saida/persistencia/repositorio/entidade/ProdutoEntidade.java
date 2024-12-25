package br.com.on.fiap.adaptadores.saida.persistencia.repositorio.entidade;

import br.com.on.fiap.hexagono.dominio.Categoria;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class ProdutoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtos_id_seq")
    @SequenceGenerator(name = "produtos_id_seq", sequenceName = "produtos_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal preco;


    public ProdutoEntidade() {
    }

    public ProdutoEntidade(Long id, String nome, Categoria categoria, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}