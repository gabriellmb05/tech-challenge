package br.com.on.fiap.hexagono.dominio;

public class PedidoFiltro {

    private Double valorMinimo;
    private Double valorMaximo;
    private Long situacao;

    public PedidoFiltro() {
    }

    public PedidoFiltro(Double valorMinimo, Double valorMaximo, Long situacao) {
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.situacao = situacao;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(Double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public Long getSituacao() {
        return situacao;
    }

    public void setSituacao(Long situacao) {
        this.situacao = situacao;
    }
}
