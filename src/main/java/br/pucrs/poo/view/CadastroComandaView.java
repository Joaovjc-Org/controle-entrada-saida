package br.pucrs.poo.view;
import br.pucrs.poo.controller.ComandaController;
import br.pucrs.poo.dto.ComandaDTO;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CadastroComandaView {
    @Autowired
    private ComandaController comandaController;

    public void fazerPedido() {
        System.out.print("Digite o ID da comanda: ");
        Scanner scanner = new Scanner(System.in);
        String comandaId = scanner.next();

        System.out.print("Digite o ID do item: ");
        Long itemId = scanner.nextLong();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        comandaController.fazerPedido(comandaId, itemId, quantidade);
    }

    public void criarComanda() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Cadastro de Comanda ===");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.next();

        try {
            ComandaDTO comandaCriada = comandaController.criarComanda(cpf);
            System.out.println("Comanda criada com sucesso!");
            System.out.println("Código da Comanda: " + comandaCriada.codigoComanda());
        } catch (RuntimeException e) {
            System.out.println("Erro ao criar comanda: " + e.getMessage());
        }
    }

    public void buscarComanda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID da comanda que deseja buscar: ");
        Long id = scanner.nextLong();

        try {
            ComandaDTO comanda = comandaController.buscarComandaPorId(id);
            System.out.println("Detalhes da Comanda:");
            System.out.println(comanda);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
