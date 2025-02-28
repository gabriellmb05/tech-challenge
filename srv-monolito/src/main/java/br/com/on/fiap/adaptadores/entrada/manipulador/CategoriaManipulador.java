package br.com.on.fiap.adaptadores.entrada.manipulador;

import br.com.on.fiap.adaptadores.entrada.manipulador.swagger.CategoriaManipuladorSwagger;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.CategoriaControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaManipulador implements CategoriaManipuladorSwagger {

    private final CategoriaControlador categoriaControlador;

    public CategoriaManipulador(CategoriaControlador categoriaControlador) {
        this.categoriaControlador = categoriaControlador;
    }

    @Override
    @GetMapping
    public ResponseEntity<CategoriaSaidaDTO> buscaCategorias() {
        CategoriaSaidaDTO categoriaSaidaDTO = categoriaControlador.buscaCategorias();
        return ResponseEntity.ok().body(categoriaSaidaDTO);
    }
}
