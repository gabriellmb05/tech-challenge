package br.com.on.fiap.adapter.input;

import br.com.on.fiap.adapter.input.swagger.CategoriaApiSwagger;
import br.com.on.fiap.core.adapter.controller.CategoriaController;
import br.com.on.fiap.core.application.dto.resposta.CategoriaRespostaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaApi implements CategoriaApiSwagger {

    private final CategoriaController categoriaController;

    @Autowired
    public CategoriaApi(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    @Override
    @GetMapping
    public ResponseEntity<CategoriaRespostaDTO> buscaCategorias() {
        CategoriaRespostaDTO categoriaSaidaDTO = categoriaController.buscaCategorias();
        return ResponseEntity.ok().body(categoriaSaidaDTO);
    }
}
