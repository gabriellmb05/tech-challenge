package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.Pagina;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaInfo<K> implements Pagina<K> {

    private List<K> conteudo;
    private Long totalElementos;
    private Integer totalPaginas;
    private Integer tamanhoPagina;
    private Integer paginaAtual;

    public static <K> PaginaInfo<K> create(Page<K> page) {
        return PaginaInfo.<K>builder()
                .conteudo(page.getContent())
                .totalElementos(page.getTotalElements())
                .totalPaginas(page.getTotalPages())
                .tamanhoPagina(page.getSize())
                .paginaAtual(page.getNumber())
                .build();
    }
}
