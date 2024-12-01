package br.pucrs.poo.controller;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
public class StartupPageController {
    
    private List<String> menuItems;
    private Map<String, Double> prices;
    private Scanner scanner;

    public void iniciarInterface() {
        this.inicializarMenu();
        boolean var1 = true;

        while (var1) {
            System.out.println("\n=== Sistema do Bar ===");
            System.out.println("1. Ver Menu");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma op\u00e7\u00e3o: ");
            int var2 = this.scanner.nextInt();
            switch (var2) {
                case 0:
                    var1 = false;
                    System.out.println("Sistema encerrado!");
                    break;
                case 1:
                    this.mostrarMenu();
                    break;
                case 2:
                    this.fazerPedido();
                    break;
                case 3:
                    this.fecharConta();
                    break;
                default:
                    System.out.println("Op\u00e7\u00e3o inv\u00e1lida!");
            }
        }

    }

    /*
    private void mostrarMenu() {
        System.out.println("\n=== Menu ===");
        Iterator var1 = this.menuItems.iterator();

        while (var1.hasNext()) {
            String var2 = (String) var1.next();
            System.out.printf("%s - R$ %.2f%n", var2, this.prices.get(var2));
        }

    }
    */
    private void fazerPedido() {
        this.mostrarMenu();
        System.out.print("\nDigite o nome do item desejado: ");
        this.scanner.nextLine();
        String var1 = this.scanner.nextLine();
        if (this.prices.containsKey(var1)) {
            System.out.println("Pedido realizado: " + var1);
            System.out.printf("Valor: R$ %.2f%n", this.prices.get(var1));
        } else {
            System.out.println("Item n\u00e3o encontrado no menu!");
        }

    }

    private void fecharConta() {
        System.out.println("Conta fechada!");
    }

}
