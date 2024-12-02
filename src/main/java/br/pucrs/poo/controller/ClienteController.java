package br.pucrs.poo.controller;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Component
public class ClienteController{
    @Autowired
    private ClienteService clienteService;
    public ClienteDTO criarCliente(ClienteDTO cliente) {
        return clienteService.cadastrarCliente(cliente);
    }
}