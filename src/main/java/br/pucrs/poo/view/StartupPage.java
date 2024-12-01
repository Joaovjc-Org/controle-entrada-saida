package br.pucrs.poo.view;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class StartupPage {
    private ItemView itemView;
    public void iniciarInterface() {
        boolean var1 = true;
        Scanner scanner = new Scanner(System.in);
        while (var1) {
            System.out.println("\n=== Sistema do Bar ===");
            System.out.println("1. Ver Menu");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int var2 = scanner.nextInt();
            switch (var2) {
                case 0 -> {
                    var1 = false;
                    System.out.println("Sistema encerrado!");
                }
                case 1 -> itemView.displayItens();
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
