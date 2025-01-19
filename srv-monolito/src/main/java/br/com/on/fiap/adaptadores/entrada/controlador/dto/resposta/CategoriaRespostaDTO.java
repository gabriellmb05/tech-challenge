package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRespostaDTO {
    private List<String> categorias;
}
