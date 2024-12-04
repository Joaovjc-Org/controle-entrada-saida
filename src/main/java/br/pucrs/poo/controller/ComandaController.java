package br.pucrs.poo.controller;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.service.ComandaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ComandaController {

    @Autowired
    private ComandaService comandaService;

    public ComandaDTO criarComanda(String cpf) {
        return comandaService.criarComanda(cpf);
    }
    public ComandaDTO buscarComandaPorId(Long id) {
        return comandaService.buscarComandaPorId(id);
    }
    public void fecharConta(String codigoComanda) {
        comandaService.fecharConta(codigoComanda);
    }
    public void fazerPedido(String codigoComanda, Long itemId, int quantidade) {
        comandaService.adicionarItem(codigoComanda, itemId, quantidade);
        System.out.println("Item adicionado com sucesso à comanda.");
    }
}
