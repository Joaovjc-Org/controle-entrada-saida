package br.pucrs.poo.view;

import br.pucrs.poo.controller.BalanceteController;
import br.pucrs.poo.dto.BalanceteDTO;

import java.util.List;

public class BalanceteView {

    private final BalanceteController balanceteController;

    public BalanceteView(BalanceteController balanceteController) {
        this.balanceteController = balanceteController;
    }

    public void exibirBalanceteDiario() {
        System.out.println("\n--- Balancete Diário ---");
        List<BalanceteDTO> balancete = balanceteController.gerarBalanceteDiario();

        if (balancete.isEmpty()) {
            System.out.println("Nenhum gasto registrado para hoje.");
            return;
        }

        for (BalanceteDTO item : balancete) {
            System.out.printf("Cliente: %s | Total Gasto: R$ %.2f%n",
                    item.getNomeCliente(), item.getTotalGasto());
        }
    }
}
