package br.com.on.fiap.adaptadores.saida.persistencia.repositorio;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ClienteEntidade;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<ClienteEntidade, Long> {

	Optional<ClienteEntidade> findByCpf(String cpf);

	Optional<ClienteEntidade> findByCpfOrEmail(String cpf, String email);
}
