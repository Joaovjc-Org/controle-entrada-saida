package br.pucrs.poo.service;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.entity.Item;
import br.pucrs.poo.mapper.ItemMapper;
import br.pucrs.poo.repository.ItemRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemMapper itemMapper;
    public List<ItemDTO> recuperarTodosItens() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toItemDTO)
                .toList();
    }
    protected Optional<Item> recuperarItemPorId(Long id) {
        return itemRepository.findById(id);
    }
}
