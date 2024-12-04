package br.com.on.fiap.aplicacao.adaptadores.controladores;

import br.com.on.fiap.aplicacao.adaptadores.dto.ProdutoDTO;
import br.com.on.fiap.dominio.portas.interfaces.IProdutoServicoPorta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {
    @Autowired
    IProdutoServicoPorta produtoServicoPorta;
    @GetMapping()
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos(){
        try {
            List<ProdutoDTO> produtos = produtoServicoPorta.getAll();

            if (produtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<ProdutoDTO>>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable("id") long id){
        ProdutoDTO produto = produtoServicoPorta.getProdutoById(id);

        if(produto != null){
            return new ResponseEntity<ProdutoDTO>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/count")
    public ResponseEntity<String> getTotalProdutos() {
        return ResponseEntity.ok("Total de produtos: " + produtoServicoPorta.getTotalProdutos());
    }
    @PostMapping()
    public ResponseEntity<String> createProduto(@RequestBody ProdutoDTO produto) {
        try {
            produtoServicoPorta.createProduto(produto);
            return new ResponseEntity<>("Produto creado com sucesso.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduto(@PathVariable("id") Long id, @RequestBody ProdutoDTO dto){
        int ret = produtoServicoPorta.updateProduto(dto);
        if(ret > 0) {
            return new ResponseEntity<>("Produto atualizado com sucesso.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produto não encontrado com id=" + id, HttpStatus.NOT_FOUND);
        }
    }
}