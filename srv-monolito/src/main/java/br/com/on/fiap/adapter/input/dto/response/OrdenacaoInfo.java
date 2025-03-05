package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.Direcao;
import br.com.on.fiap.core.domain.model.Ordenacao;
import lombok.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Data
@RequiredArgsConstructor(staticName = "of")
public class OrdenacaoInfo implements Ordenacao {

    private String campo;
    private Direcao direcao;

    public static OrdenacaoInfo from(Order order) {
        OrdenacaoInfo response = new OrdenacaoInfo();
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
