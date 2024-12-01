package br.pucrs.poo.view;

import java.util.Scanner;

import javax.swing.*;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class StartupPage {
    
    private Scanner scanner;

    public void iniciarInterface() {
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
}
