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

    public ComandaDTO criarComanda(Long clienteId, Long folhaId) {
        ComandaDTO novaComanda = new ComandaDTO(null, null, null, null, null, clienteId, folhaId);
        return comandaService.criarComanda(novaComanda);
    }

    public ComandaDTO buscarComandaPorId(Long id) {
        return comandaService.buscarComandaPorId(id);
    }
}
