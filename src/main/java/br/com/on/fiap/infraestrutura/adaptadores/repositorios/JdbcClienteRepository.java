package br.com.on.fiap.infraestrutura.adaptadores.repositorios;

import br.com.on.fiap.dominio.Cliente;
import br.com.on.fiap.dominio.portas.repositorios.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcClienteRepository implements IClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * @param cpf
     * @return
     */
    @Override
    public Cliente findByCpf(String cpf) {
        try {
            Cliente cliente = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE cpf=?",
                    BeanPropertyRowMapper.newInstance(Cliente.class), cpf);

            return cliente;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Cliente> findAll() {
        return jdbcTemplate.query("SELECT * FROM CLIENTES", BeanPropertyRowMapper.newInstance(Cliente.class));
    }
}
