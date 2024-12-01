package br.pucrs.poo.view;


import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.dto.ClienteDTO;


import java.util.List;

public class ClienteView {

    private ClienteController clienteController;

    public void mostrarClientes() {
        clienteController.listarClientes();
        System.out.println("\n=== Lista de Clientes ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (ClienteDTO cliente : clientes) {
            System.out.printf("Nome: %s | CPF: %s | Telefone: %s%n", 
                              cliente.nome(), 
                              cliente.cpf(),
                              cliente.telefone());
        }
    }
}
