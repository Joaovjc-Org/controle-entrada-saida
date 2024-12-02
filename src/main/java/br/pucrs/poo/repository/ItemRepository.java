package br.pucrs.poo.repository;
import br.pucrs.poo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<Item, Long> {
}
