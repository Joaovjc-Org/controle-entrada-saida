package br.pucrs.poo.view;

import br.pucrs.poo.repository.ItemRepository;

public class ItemView {
    public void displayItems(java.util.List<ItemRepository> items) {
        System.out.println("\n--- Cardápio ---");
        for (int i = 0; i < items.size(); i++) {
            ItemRepository item = items.get(i);
            System.out.printf("%d. %s - R$ %.2f%n", i + 1, item.getName(), item.getPrice());
        }
    }
}
