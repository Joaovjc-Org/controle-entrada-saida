package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {
    Optional<Comanda> findById(Long id);
    Optional<Comanda> findByClienteIdAndDataPagamentoIsNull(Long clienteId);
    Optional<Comanda> findByCodigoComanda(String codigoComanda);
    List<Comanda> findAllByDataCriacaoBetween(LocalDateTime data, LocalDateTime data2);
    List<Comanda> findAllByDataPagamentoNotNull();
}
