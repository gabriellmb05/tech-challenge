package br.com.on.fiap.core.application.dto.resposta.paginacao;

public interface PaginacaoResposta {
    Integer getPagina();

    Integer getTamanhoPagina();

    OrdenacaoResposta getOrdenacaoResposta();

    static PaginacaoResposta create(Integer pagina, Integer tamanhoPagina, OrdenacaoResposta ordenacaoResposta) {
        return new PaginacaoResposta() {
            @Override
            public Integer getPagina() {
                return pagina;
            }

            @Override
            public Integer getTamanhoPagina() {
                return tamanhoPagina;
            }

            @Override
            public OrdenacaoResposta getOrdenacaoResposta() {
                return ordenacaoResposta;
            }
        };
    }
}
