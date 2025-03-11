package br.com.on.fiap.adapter.input.dto.resposta;

import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaRespostaInfo<K> implements PaginaResposta<K> {

    private List<K> conteudo;
    private Long totalElementos;
    private Integer totalPaginas;
    private Integer tamanhoPagina;
    private Integer paginaAtual;

    public static <K> PaginaRespostaInfo<K> create(Page<K> page) {
        return PaginaRespostaInfo.<K>builder()
                .conteudo(page.getContent())
                .totalElementos(page.getTotalElements())
                .totalPaginas(page.getTotalPages())
                .tamanhoPagina(page.getSize())
                .paginaAtual(page.getNumber())
                .build();
    }

    @Override
    public <U> PaginaResposta<U> map(Function<? super K, ? extends U> converter) {
        List<U> novoConteudo = conteudo.stream().map(converter).collect(Collectors.toList());

        return PaginaRespostaInfo.<U>builder()
                .conteudo(novoConteudo)
                .totalElementos(totalElementos)
                .totalPaginas(totalPaginas)
                .tamanhoPagina(tamanhoPagina)
                .paginaAtual(paginaAtual)
                .build();
    }
}
