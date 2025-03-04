package br.com.on.fiap.adaptadores.cliente.saida.persistencia.repositorio;

import br.com.on.fiap.entidades.ClienteEntidade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteEntidade, Long> {

    Optional<ClienteEntidade> findByNmCpf(String cpf);

    Optional<ClienteEntidade> findByNmEmail(String email);
}
