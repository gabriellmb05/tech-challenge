package br.com.on.fiap.core.domain.entity;

import java.time.LocalDate;

public class PedidoFiltro {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long situacao;
    private String cpfCliente;

    public PedidoFiltro() {}

    public PedidoFiltro(LocalDate dataInicio, LocalDate dataFim, Long situacao, String cpfCliente) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.situacao = situacao;
        this.cpfCliente = cpfCliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getSituacao() {
        return situacao;
    }

    public void setSituacao(Long situacao) {
        this.situacao = situacao;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}
