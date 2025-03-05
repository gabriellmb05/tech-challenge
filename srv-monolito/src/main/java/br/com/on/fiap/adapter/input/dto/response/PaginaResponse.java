package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.Pagina;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaResponse<K> implements Pagina<K> {

    private List<K> conteudo;
    private Long totalElementos;
    private Integer totalPaginas;
    private Integer tamanhoPagina;
    private Integer paginaAtual;
}
