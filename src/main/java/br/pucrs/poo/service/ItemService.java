package br.pucrs.poo.service;

import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.mapper.ItemMapper;
import br.pucrs.poo.repository.ItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<ItemDTO> recuperarTodosItens() {
        return itemRepository.findAll().stream()
                .map(ItemMapper.INSTANCE::toItemDTO)
                .toList();
    }
}
