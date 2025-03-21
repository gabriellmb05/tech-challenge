package br.com.on.fiap.core.domain;

import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;

    public Produto() {}

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(Long id, String nome, Categoria categoria, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId())
                && Objects.equals(getNome(), produto.getNome())
                && getCategoria() == produto.getCategoria()
                && Objects.equals(getPreco(), produto.getPreco());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getNome());
        result = 31 * result + Objects.hashCode(getCategoria());
        result = 31 * result + Objects.hashCode(getPreco());
        return result;
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

    public void atualizarDados(ProdutoEntrada produtoEntrada) {
        this.nome = produtoEntrada.getNome();
        this.categoria = Categoria.deString(produtoEntrada.getCategoria());
        this.preco = produtoEntrada.getPreco();
    }
}
