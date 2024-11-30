package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Cliente;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
