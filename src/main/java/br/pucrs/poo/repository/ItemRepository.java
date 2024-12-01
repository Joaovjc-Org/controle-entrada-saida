package br.pucrs.poo.repository;

public class ItemRepository {
    private String name;
    private double price;

    public ItemRepository(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
