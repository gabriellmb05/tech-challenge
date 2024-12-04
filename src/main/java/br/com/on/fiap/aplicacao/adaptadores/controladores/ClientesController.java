package br.com.on.fiap.aplicacao.adaptadores.controladores;

import br.com.on.fiap.dominio.Cliente;
import br.com.on.fiap.dominio.portas.repositorios.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    IClienteRepository ClienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/count")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok("Total de clientes: " + ClienteRepository.findAll().stream().count());
    }
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> getUsersByCpf(@PathVariable("cpf") String cpf) {
        Cliente cliente = ClienteRepository.findByCpf(cpf);
        if(cliente!=null){
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAlClientes() {
        try {

            List<Cliente> clientes = new ArrayList<>();

            ClienteRepository.findAll().forEach(clientes::add);

            if (clientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}