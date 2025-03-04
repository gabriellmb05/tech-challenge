package br.com.on.fiap.adaptadores.categoria.entrada;

import br.com.on.fiap.adaptadores.categoria.CategoriaManipuladorSwagger;
import br.com.on.fiap.hexagono.adapter.controller.CategoriaController;
import br.com.on.fiap.hexagono.usecase.dto.CategoriaSaidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaManipulador implements CategoriaManipuladorSwagger {

    private final CategoriaController categoriaController;

    @Autowired
    public CategoriaManipulador(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    @Override
    @GetMapping
    public ResponseEntity<CategoriaSaidaDTO> buscaCategorias() {
        CategoriaSaidaDTO categoriaSaidaDTO = categoriaController.buscaCategorias();
        return ResponseEntity.ok().body(categoriaSaidaDTO);
    }
}
