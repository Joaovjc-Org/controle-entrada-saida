package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
}
