package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import br.pucrs.poo.view.ClienteView;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Component



@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private ClienteView view;
    private Scanner scanner;

    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }
    
    public ClienteDTO cadastrarCliente(String nome, String cpf) {
        ClienteDTO novoCliente = new ClienteDTO(nome, cpf, "", "");
        return clienteService.cadastrarCliente(novoCliente);
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return clienteService.buscarClientePorId(id);
    }

    public List<ClienteDTO> buscarClientesPorNome(String nome) {
        return clienteService.buscarClientesPorNome(nome);
    }
}