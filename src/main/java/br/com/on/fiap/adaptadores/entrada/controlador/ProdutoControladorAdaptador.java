package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ProdutoMapeador;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoControladorAdaptador {


    @Autowired
    private BuscaProdutoPorIdPortaEntrada buscaProdutoPorIdPortaEntrada;

    @Autowired
    private InsereProdutoPortaEntrada insereProdutoPortaEntrada;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscaProdutoPorId(@PathVariable("id") long id){
        Produto produto = buscaProdutoPorIdPortaEntrada.buscar(id);
        ProdutoDTO produtoDTO = ProdutoMapeador.produtoParaProdutoDTO(produto);
        return new ResponseEntity<ProdutoDTO>(produtoDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProdutoDTO> InsereProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapeador.produtoDTOParaProduto(produtoDTO);
        Produto produtoPersistido = insereProdutoPortaEntrada.inserir(produto);
        ProdutoDTO produtoPersistidoDTO = ProdutoMapeador.produtoParaProdutoDTO(produtoPersistido);
        return new ResponseEntity<ProdutoDTO>(produtoPersistidoDTO, HttpStatus.CREATED);
    }

}
