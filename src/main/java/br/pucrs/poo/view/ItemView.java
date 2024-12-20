package br.pucrs.poo.view;
import br.pucrs.poo.controller.ItemController;
import br.pucrs.poo.dto.ItemDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ItemView {
    @Autowired
    private ItemController itemController;
    public void displayItens() {
        System.out.println("\n--- Cardápio ---");
        List<ItemDTO> itens = itemController.recuperarTodosItensCadastrados();
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d. %s - R$ %.2f%n", i + 1, itens.get(i).nome(), itens.get(i).preco());
        }
    }
}