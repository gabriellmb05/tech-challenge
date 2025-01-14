package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.ProdutoFiltro;

public class ProdutoFiltroDTO implements ProdutoFiltro {

  private Categoria categoria;
  private String nome;

  public ProdutoFiltroDTO() {}

  public ProdutoFiltroDTO(Categoria categoria, String nome) {
    this.categoria = categoria;
    this.nome = nome;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
