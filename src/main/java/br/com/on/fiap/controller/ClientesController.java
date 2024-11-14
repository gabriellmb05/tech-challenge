package br.com.on.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<String> getUsers() {
        String query = "SELECT COUNT(*) FROM clientes";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return ResponseEntity.ok("Total customers: " + count);
    }
}
