package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.service.ItemService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ItemController {
    private ItemService itemService;
    public List<ItemDTO> recuperarTodosItensCadastrados(){
        return itemService.recuperarTodosItens();
    }
}
