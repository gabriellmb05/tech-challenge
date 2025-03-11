package br.com.on.fiap.adapter.input.dto.resposta;

import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.OrdenacaoResposta;
import lombok.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Data
@RequiredArgsConstructor(staticName = "of")
public class OrdenacaoRespostaInfo implements OrdenacaoResposta {

    private String campo;
    private Direcao direcao;

    public static OrdenacaoRespostaInfo from(Order order) {
        OrdenacaoRespostaInfo response = new OrdenacaoRespostaInfo();
        response.setCampo(order.getProperty());

        Direction direction = order.getDirection();
        response.setDirecao(Direcao.valueOf(direction.name()));
        return response;
    }

    @Override
    public String getCampo() {
        return campo;
    }

    @Override
    public Direcao getDirecao() {
        return direcao;
    }
}
