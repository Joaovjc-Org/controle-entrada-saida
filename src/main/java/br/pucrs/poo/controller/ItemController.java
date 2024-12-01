package br.pucrs.poo.controller;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.poo.repository.ItemRepository;
import br.pucrs.poo.view.ItemView;

public class ItemController {
    private List<ItemRepository> items;
    private ItemView view;

    public ItemController() {
        this.items = new ArrayList<>();
        this.view = new ItemView();
    }

    public void addItem(String name, double price) {
        items.add(new ItemRepository(name, price));
    }

    public List<ItemRepository> getItems() {
        return items;
    }

    public void showItems() {
        view.displayItems(items);
    }

    public static void main(String[] args) {
        ItemController controller = new ItemController();

        // Adicionando itens ao cardápio
        controller.addItem("Caipirinha", 12.00);
        controller.addItem("Cerveja", 8.50);
        controller.addItem("Refrigerante", 6.00);
        controller.addItem("Água", 4.00);

        // Exibindo o cardápio
        controller.showItems();
    }
}
