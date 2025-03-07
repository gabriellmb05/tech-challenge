package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.application.dto.resposta.Ordenacao;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import java.util.Optional;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

@Data
@RequiredArgsConstructor(staticName = "of")
public class PaginacaoInfo implements Paginacao {

    private Integer pagina;
    private Integer tamanhoPagina;
    private Ordenacao ordenacao;

    public static PaginacaoInfo from(Pageable pageable) {
        PaginacaoInfo response = new PaginacaoInfo();
        response.setPagina(pageable.getPageNumber());
        response.setTamanhoPagina(pageable.getPageSize());

        Optional<Order> ordem = pageable.getSort().stream().findFirst();
        ordem.ifPresent(order -> response.setOrdenacao(OrdenacaoInfo.from(order)));
        return response;
    }

    @Override
    public Integer getPagina() {
        return pagina;
    }

    @Override
    public Integer getTamanhoPagina() {
        return tamanhoPagina;
    }

    @Override
    public Ordenacao getOrdenacao() {
        return ordenacao;
    }
}
