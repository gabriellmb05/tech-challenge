package br.com.on.fiap.infraestrutura.adaptadores.repositorios;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.dominio.portas.repositorios.IProdutoRepositorioPorta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProdutoRepository implements IProdutoRepositorioPorta {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * @param produto
     * @return
     */
    @Override
    public int save(Produto produto) {
        return jdbcTemplate.update("INSERT INTO Produtos(Nome, Categoria, Preco, Ingredientes) VALUES(?,?,?,?)",
                new Object[] { produto.getNome(), produto.getCategoria().toString(), produto.getPreco(), produto.getIngredientes()});
    }

    @Override
    public int update(Long id, Produto produto) {
        return jdbcTemplate.update("UPDATE PRODUTOS SET Nome=?, Categoria=?, Preco=?, Ingredientes=? WHERE Id=?",
                new Object[] { produto.getNome(), produto.getCategoria().toString(), produto.getPreco(), produto.getIngredientes(), produto.getId()});
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Produto findById(Long id) {
        try{
            Produto entity = jdbcTemplate.queryForObject("SELECT * FROM PRODUTOS WHERE ID = ?",
                    BeanPropertyRowMapper.newInstance(Produto.class), id);
            return entity;
        }
        catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM Produtos WHERE id=?", id);
    }

    /**
     * @return
     */
    @Override
    public List<Produto> findAll() {
        return jdbcTemplate.query("SELECT Id, Nome, Categoria, CAST(Preco AS decimal(18, 2)) as Preco, Ingredientes FROM PRODUTOS", BeanPropertyRowMapper.newInstance(Produto.class));
    }

    /**
     * @param categoria
     * @return
     */
    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        return null;
    }

    /**
     * @param nome
     * @return
     */
    @Override
    public List<Produto> findByNomeContaining(String nome) {
        return null;
    }
}
