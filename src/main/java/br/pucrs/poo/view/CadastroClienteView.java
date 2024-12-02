package br.pucrs.poo.view;

import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class CadastroClienteView {
    @Autowired
    private ClienteController clienteController;

    public void iniciarInterface() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Menu Cliente ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    buscarCliente(scanner);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarCliente(Scanner scanner) {
        try {
            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();

            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();

            ClienteDTO cliente = clienteController.cadastrarCliente(nome, cpf);
            System.out.println("Cliente cadastrado com sucesso: " + cliente);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente(Scanner scanner) {
        System.out.println("\n=== Buscar Cliente ===");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nome");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        switch (opcao) {
            case 1:
                buscarClientePorId(scanner);
                break;
            case 2:
                buscarClientePorNome(scanner);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void buscarClientePorId(Scanner scanner) {
        System.out.print("Digite o ID do cliente: ");
        Long id = scanner.nextLong();

        try {
            ClienteDTO cliente = clienteController.buscarClientePorId(id);
            System.out.println("Cliente encontrado: " + cliente);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarClientePorNome(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        try {
            List<ClienteDTO> clientes = clienteController.buscarClientesPorNome(nome);
            System.out.println("Clientes encontrados:");
            for (ClienteDTO cliente : clientes) {
                System.out.println(cliente);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
