package br.pucrs.poo.view;
import br.pucrs.poo.controller.ComandaController;
import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.controller.StartupPageController;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CadastroComandaView {
    @Autowired
    private ComandaController comandaController;
    @Autowired
    private StartupPageController startupPageController;
    private Scanner scanner;

    public void fazerPedido() {
        System.out.print("Digite o ID da comanda: ");
        Long comandaId = scanner.nextLong();

        System.out.print("Digite o ID do item: ");
        Long itemId = scanner.nextLong();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        startupPageController.fazerPedido(comandaId, itemId, quantidade);
    }

    public CadastroComandaView() {
        this.scanner = new Scanner(System.in);
    }

    public void criarComanda() {
        System.out.println("\n=== Cadastro de Comanda ===");

        System.out.print("Digite o ID do cliente: ");
        Long clienteId = scanner.nextLong();

        System.out.print("Digite o ID da folha associada: ");
        Long folhaId = scanner.nextLong();

        try {
            ComandaDTO comandaCriada = comandaController.criarComanda(clienteId, folhaId);
            System.out.println("Comanda criada com sucesso!");
            System.out.println("Código da Comanda: " + comandaCriada.getCodigoComanda());
        } catch (RuntimeException e) {
            System.out.println("Erro ao criar comanda: " + e.getMessage());
        }
    }

    public void buscarComanda() {
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
