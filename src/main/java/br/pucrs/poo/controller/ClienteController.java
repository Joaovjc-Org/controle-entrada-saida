package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import br.pucrs.poo.view.ClienteView;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private ClienteView view;
    private Scanner scanner;

    public void iniciarInterface() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Sistema de Clientes ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 0:
                    running = false;
                    System.out.println("Sistema encerrado!");
                    break;
                case 1:
                    criarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public List <ClienteDTO> listarClientes() {
       return clienteService.listarClientes();
    }
    
    public ClienteDTO criarCliente(ClienteDTO cliente) {
        return clienteService.cadastrarCliente(cliente);
    }
}
