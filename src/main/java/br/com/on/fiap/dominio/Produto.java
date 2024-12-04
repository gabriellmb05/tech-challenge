package br.com.on.fiap.dominio;

import br.com.on.fiap.aplicacao.adaptadores.dto.ProdutoDTO;

import java.math.BigDecimal;

public class Produto {
    private Long Id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String ingredientes = "";

    public Produto(){}
    public Produto(ProdutoDTO dto) {
        this.nome = dto.getNome();
        this.categoria = dto.getCategoria();
        this.preco = dto.getPreco();
        this.ingredientes = dto.getIngredientes();
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long id) { this.Id = id; }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ProdutoDTO toProdutoDTO() {
        return new ProdutoDTO(this.Id, this.nome, this.categoria, this.preco, this.ingredientes);
    }

/*
    public List<String> getListIngredientes(){
        if(!this.ingredientes.isEmpty()){
            return List.of(this.ingredientes.split(","));
        }

        return new ArrayList<String>();
    }

    public void addIngrediente(String ingrediente){
        if(this.ingredientes.isEmpty()){
            this.ingredientes = ingrediente;
        } else if (!this.ingredientes.toUpperCase().contains(ingrediente.toUpperCase())){
            this.ingredientes.concat(", ").concat(ingrediente);
        }
    }

    public void addListaIngredientes(List<String> ingredientes){
        ingredientes.forEach(item -> addIngrediente(item));
    }

    public void removeIngrediente(String ingrediente){
        if (this.ingredientes.toUpperCase().contains(ingrediente.toUpperCase())) {
            this.ingredientes
                    .toUpperCase()
                    .replace(ingrediente.toUpperCase() + ", ", "")
                    .replace(", " + ingrediente.toUpperCase(), "")
                    .replace(ingrediente.toUpperCase(), "");
        }
    }
*/
}
