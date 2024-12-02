package br.pucrs.poo.view;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CadastroComandaView {
    private final ComandaService comandaService;
    private final Scanner scanner;
    public void criarComanda() {
        System.out.println("\n=== Cadastro de Comanda ===");

        System.out.print("Digite o ID do cliente: ");
        Long clienteId = scanner.nextLong();

        System.out.print("Digite o ID da folha associada: ");
        Long folhaId = scanner.nextLong();

        ComandaDTO novaComanda = new ComandaDTO(null, null, null, null, null, clienteId, folhaId);

        try {
            ComandaDTO comandaCriada = comandaService.criarComanda(novaComanda);
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
            ComandaDTO comanda = comandaService.buscarComandaPorId(id);
            System.out.println("Detalhes da Comanda:");
            System.out.println(comanda);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
