package br.com.on.fiap.adapter.output.db.repository;

import br.com.on.fiap.adapter.output.db.entity.ClienteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByNmCpf(String cpf);

    Optional<ClienteEntity> findByNmEmail(String email);
}
