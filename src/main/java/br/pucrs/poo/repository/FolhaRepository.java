package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Folha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FolhaRepository extends JpaRepository<Folha, Long> {
}
