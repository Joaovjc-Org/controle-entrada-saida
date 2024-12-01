package br.pucrs.poo.service;

import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.mapper.ItemMapper;
import br.pucrs.poo.repository.ItemRepository;

import java.util.List;

public class ItemService {
    private ItemRepository itemRepository;
    public List<ItemDTO> recuperarTodosItens() {
        return itemRepository.findAll().stream()
                .map(ItemMapper.INSTANCE::toItemDTO)
                .toList();
    }
}
