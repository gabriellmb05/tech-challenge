package br.com.on.fiap.dominio.adaptadores;

import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.aplicacao.adaptadores.dto.ProdutoDTO;
import br.com.on.fiap.dominio.adaptadores.*;
import br.com.on.fiap.dominio.mapeadores.ProdutoMap;
import br.com.on.fiap.dominio.portas.interfaces.IProdutoServicoPorta;
import br.com.on.fiap.dominio.portas.repositorios.IProdutoRepositorioPorta;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProdutoServicoImp implements IProdutoServicoPorta {
    @Autowired
    private final IProdutoRepositorioPorta produtoRepositorioPorta;

    public ProdutoServicoImp(IProdutoRepositorioPorta produtoRepositorioPorta) {
        this.produtoRepositorioPorta = produtoRepositorioPorta;
    }

    @Override
    public List<ProdutoDTO> getAll() {
        try {
            List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();

            produtoRepositorioPorta.findAll().forEach(item -> produtos.add(ProdutoMap.mapToProdutoDTO(item)));

            return produtos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ProdutoDTO getProdutoById(long id) {
        return ProdutoMap.mapToProdutoDTO(produtoRepositorioPorta.findById(id));
    }

    @Override
    public String getTotalProdutos() {
        return produtoRepositorioPorta.findAll().stream().count() + "";
    }

    @Override
    public int createProduto(ProdutoDTO dto) {
        return produtoRepositorioPorta.save(ProdutoMap.mapToProduto(dto));
    }

    @Override
    public int updateProduto(ProdutoDTO dto) {
        Produto entidade = produtoRepositorioPorta.findById(dto.getId());
        if (entidade != null) {
            return produtoRepositorioPorta.update(dto.getId(), ProdutoMap.mapToProduto(dto));
        } else {
            return 0;
        }
    }
}
