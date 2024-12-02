package br.pucrs.poo.controller;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.service.ComandaService;
import java.util.List;

public class ComandaController {
    private final ComandaService comandaService;

    

    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    public ComandaDTO criarComanda(Long clienteId, Long folhaId) {
        ComandaDTO novaComanda = new ComandaDTO(null, null, null, null, null, clienteId, folhaId);
        return comandaService.criarComanda(novaComanda);
    }

    public ComandaDTO buscarComandaPorId(Long id) {
        return comandaService.buscarComandaPorId(id);
    }
}
