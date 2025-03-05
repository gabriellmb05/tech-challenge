package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.Ordenacao;
import br.com.on.fiap.core.domain.model.Paginacao;
import java.util.Optional;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

@Data
@RequiredArgsConstructor(staticName = "of")
public class PaginacaoResponse implements Paginacao {

    private Integer pagina;
    private Integer tamanhoPagina;
    private Ordenacao ordenacao;

    public static PaginacaoResponse from(Pageable pageable) {
        PaginacaoResponse response = new PaginacaoResponse();
        response.setPagina(pageable.getPageNumber());
        response.setTamanhoPagina(pageable.getPageSize());

        Optional<Order> ordem = pageable.getSort().stream().findFirst();
        ordem.ifPresent(order -> response.setOrdenacao(OrdenacaoResponse.from(order)));
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
