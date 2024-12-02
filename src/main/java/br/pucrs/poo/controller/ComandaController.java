package br.pucrs.poo.controller;

import java.util.Scanner;

import br.pucrs.poo.service.ComandaService;
import br.pucrs.poo.view.CadastroComandaView;

public class ComandaController {

    private final CadastroComandaView comandaView;

    public ComandaController(ComandaService comandaService) {
        this.comandaView = new CadastroComandaView(comandaService);
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Sistema de Comandas ===");
            System.out.println("1. Criar Comanda");
            System.out.println("2. Buscar Comanda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            Scanner scanner = new Scanner(System.in);
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> comandaView.criarComanda();
                case 2 -> comandaView.buscarComanda();
                case 0 -> {
                    continuar = false;
                    System.out.println("Sistema encerrado!");
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
