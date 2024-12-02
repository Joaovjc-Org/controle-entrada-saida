package br.pucrs.poo.view;

import br.pucrs.poo.controller.StartupPageController;
import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.service.ComandaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class StartupPage {
    @Autowired
    private final StartupPageController startupPageController;
    private final ItemView itemView;
    private final ComandaService comandaService;

    

    public void iniciarInterface() {
        boolean var1 = true;
        Scanner scanner = new Scanner(System.in);
        while (var1) {
            System.out.println("\n=== Sistema do Bar ===");
            System.out.println("1. Ver Menu");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("4. Fechar Dia");
            System.out.println("5. Iniciar Novo Dia");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int var2 = scanner.nextInt();
            switch (var2) {
                case 0 -> {
                    var1 = false;
                    System.out.println("Sistema encerrado!");
                }
                case 1 -> itemView.displayItens();
                case 2 -> {
                    try {
                        comandaService.verificarSistemaAtivo();
                        CadastroComandaView.fazerPedido();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Digite o ID da comanda: ");
                    String comandaId = scanner.nextLine();
                    startupPageController.fecharConta(comandaId);
                }
                case 4 -> startupPageController.fecharDia();
                case 5 -> startupPageController.iniciarNovoDia();
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
    

    private void gerarBalanceteDiario() {
        System.out.println("\n--- Balancete Diário ---");
        List<GastoTotalDTO> balancete = startupPageController.gerarBalanceteDiario();

        if (balancete.isEmpty()) {
            System.out.println("Nenhum gasto registrado para hoje.");
            return;
        }

        for (GastoTotalDTO item : balancete) {
            System.out.printf("Cliente: %s | Total Gasto: R$ %.2f%n",
                    item.nomeCliente(), item.gastoTotal());
        }
    }

    private void fecharConta() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Digite o ID da comanda: ");
        String comandaId = scanner.nextLine();
    
        try {
            startupPageController.fecharConta(comandaId);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    

    
}
