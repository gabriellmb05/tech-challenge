package br.com.on.fiap.adapter.input.dto.resposta;

import br.com.on.fiap.core.application.dto.resposta.OrdenacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import java.util.Optional;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

@Data
@RequiredArgsConstructor(staticName = "of")
public class PaginacaoRespostaInfo implements PaginacaoResposta {

    private Integer pagina;
    private Integer tamanhoPagina;
    private OrdenacaoResposta ordenacaoResposta;

    public static PaginacaoRespostaInfo from(Pageable pageable) {
        PaginacaoRespostaInfo response = new PaginacaoRespostaInfo();
        response.setPagina(pageable.getPageNumber());
        response.setTamanhoPagina(pageable.getPageSize());

        Optional<Order> ordem = pageable.getSort().stream().findFirst();
        ordem.ifPresent(order -> response.setOrdenacaoResposta(OrdenacaoRespostaInfo.from(order)));
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

    public OrdenacaoResposta getOrdenacaoResposta() {
        return ordenacaoResposta;
    }
}
