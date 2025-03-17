package br.com.on.fiap.core.application.dto.entrada;

public interface ProdutoQuantidadeEntrada {

    Long getIdProduto();

    Long getQuantidade();

    static ProdutoQuantidadeEntrada create(Long idProduto, Long quantidade) {
        return new ProdutoQuantidadeEntrada() {
            @Override
            public Long getIdProduto() {
                return idProduto;
            }

            @Override
            public Long getQuantidade() {
                return quantidade;
            }
        };
    }
}
