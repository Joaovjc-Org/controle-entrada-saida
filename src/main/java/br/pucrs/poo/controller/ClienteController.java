package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import br.pucrs.poo.view.ClienteView;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private ClienteView view;
    private Scanner scanner;

    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }
    
    public ClienteDTO criarCliente(ClienteDTO cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return clienteService.buscarClientePorId(id);
    }

    public List<ClienteDTO> buscarClientesPorNome(String nome) {
        return clienteService.buscarClientesPorNome(nome);
    }
}