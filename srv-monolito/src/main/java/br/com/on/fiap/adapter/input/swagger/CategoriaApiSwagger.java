package br.com.on.fiap.adapter.input.swagger;

import br.com.on.fiap.core.application.dto.resposta.categoria.CategoriaResposta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Categoria", description = "APIs relacionadas a categorias")
public interface CategoriaApiSwagger {

    @Operation(summary = "Busca categorias", description = "Retorna uma lista de categorias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Categorias disponíveis")})
    ResponseEntity<CategoriaResposta> buscaCategorias();
}
