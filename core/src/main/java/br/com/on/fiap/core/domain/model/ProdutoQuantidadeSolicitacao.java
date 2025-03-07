package br.com.on.fiap.core.domain.model;

public interface ProdutoQuantidadeSolicitacao {

    Long getIdProduto();

    Long getQuantidade();

    static ProdutoQuantidadeSolicitacao create(Long idProduto, Long quantidade) {
        return new ProdutoQuantidadeSolicitacao() {
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
