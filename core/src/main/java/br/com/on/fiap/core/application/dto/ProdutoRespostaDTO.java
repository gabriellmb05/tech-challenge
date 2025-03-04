package br.com.on.fiap.core.application.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoRespostaDTO {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;

    public ProdutoRespostaDTO() {}

    public ProdutoRespostaDTO(Long id, String nome, String categoria, BigDecimal preco) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProdutoRespostaDTO that = (ProdutoRespostaDTO) o;
        return Objects.equals(id, that.id)
                && Objects.equals(nome, that.nome)
                && Objects.equals(categoria, that.categoria)
                && Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(nome);
        result = 31 * result + Objects.hashCode(categoria);
        result = 31 * result + Objects.hashCode(preco);
        return result;
    }

    @Override
    public String toString() {
        return "ProdutoRespostaDTO{" + "id="
                + id + ", nome='"
                + nome + '\'' + ", categoria='"
                + categoria + '\'' + ", preco="
                + preco + '}';
    }
}
