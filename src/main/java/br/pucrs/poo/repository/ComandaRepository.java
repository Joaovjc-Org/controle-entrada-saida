package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Comanda;
import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;
import java.util.Optional;
@Repository
public interface ComandaRepository extends CrudRepository<Comanda, Long> {
    @Override
    Optional<Comanda> findById(Long id);
}
