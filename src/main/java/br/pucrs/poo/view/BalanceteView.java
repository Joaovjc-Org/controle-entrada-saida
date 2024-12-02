package br.pucrs.poo.view;

import br.pucrs.poo.controller.BalanceteController;
import br.pucrs.poo.dto.GastoTotalDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class BalanceteView {
    @Autowired
    private final BalanceteController balanceteController;


    public void exibirBalanceteDiario() {
        System.out.println("\n--- Balancete Diário ---");
        List<GastoTotalDTO> balancete = balanceteController.gerarBalanceteDiario();

        if (balancete.isEmpty()) {
            System.out.println("Nenhum gasto registrado para hoje.");
            return;
        }

        for (GastoTotalDTO item : balancete) {
            System.out.printf("Cliente: %s | Total Gasto: R$ %.2f%n",
                    item.nomeCliente(), item.gastoTotal());
        }
    }
}
