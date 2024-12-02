package br.pucrs.poo.view;


import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.util.List;
@Component
public class ClienteView {
    @Autowired
    private ClienteController clienteController;

    public void mostrarClientes() {
        
        List<ClienteDTO>  clientes = clienteController.listarClientes();
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
