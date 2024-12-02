package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;
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