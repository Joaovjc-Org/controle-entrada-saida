package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Folha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface FolhaRepository extends JpaRepository<Folha, Long> {
    Optional<Folha> findByDataAberturaAndFechadoIsFalse(LocalDate dataAbertura);
}
