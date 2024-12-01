package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import lombok.RequiredArgsConstructor;
public class ClienteController {
    private ClienteService clienteService;
    public ClienteDTO criarCliente(ClienteDTO cliente) {
        return clienteService.cadastrarCliente(cliente);
    }
}
