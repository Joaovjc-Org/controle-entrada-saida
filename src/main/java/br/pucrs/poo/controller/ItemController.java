package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ItemController {
    @Autowired
    private ItemService itemService;
    public List<ItemDTO> recuperarTodosItensCadastrados(){
        return itemService.recuperarTodosItens();
    }
}