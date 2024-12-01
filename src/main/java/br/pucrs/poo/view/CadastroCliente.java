package br.pucrs.poo.view;

import javax.swing.*;


import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;

import java.util.Scanner;

public class CadastroCliente {

    private final ClienteService clienteService;
    private final Scanner scanner;

    public CadastroCliente(ClienteService clienteService) {
        this.clienteService = clienteService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarCadastro() {
        System.out.println("\n=== Cadastro de Cliente ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        ClienteDTO clienteDTO = new ClienteDTO(nome, cpf, email, telefone);

        try {
            ClienteDTO clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.printf("Nome: %s | CPF: %s%n", clienteCadastrado.nome(), clienteCadastrado.cpf());
        } catch (RuntimeException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}

