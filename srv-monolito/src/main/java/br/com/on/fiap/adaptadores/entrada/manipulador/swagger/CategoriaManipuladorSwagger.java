package br.com.on.fiap.adaptadores.entrada.manipulador.swagger;

import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Categoria", description = "APIs relacionadas a categorias")
public interface CategoriaManipuladorSwagger {

    @Operation(summary = "Busca categorias", description = "Retorna uma lista de categorias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Categorias disponíveis")})
    ResponseEntity<CategoriaSaidaDTO> buscaCategorias();
}
