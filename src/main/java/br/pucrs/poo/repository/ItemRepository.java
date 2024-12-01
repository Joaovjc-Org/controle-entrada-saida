package br.pucrs.poo.repository;

import br.pucrs.poo.entity.Item;
import jakarta.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
