package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
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
    public Optional<ClienteDTO> buscarClientesPorCPF(String cpf) {
        return clienteService.buscarClientesPorCPF(cpf);
    }
}