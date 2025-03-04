package br.com.on.fiap.adaptadores.categoria.entrada;

import br.com.on.fiap.adaptadores.categoria.CategoriaManipuladorSwagger;
import br.com.on.fiap.hexagono.adaptadores.controladores.CategoriaControlador;
import br.com.on.fiap.hexagono.adaptadores.dto.CategoriaSaidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaManipulador implements CategoriaManipuladorSwagger {

    private final CategoriaControlador categoriaControlador;

    @Autowired
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
