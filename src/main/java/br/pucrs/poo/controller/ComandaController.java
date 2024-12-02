package br.pucrs.poo.controller;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.service.ComandaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class ComandaController {

    @Autowired
    private final ComandaService comandaService;

    public ComandaDTO criarComanda(Long clienteId, Long folhaId) {
        ComandaDTO novaComanda = new ComandaDTO(null, null, null, null, null, clienteId, folhaId);
        return comandaService.criarComanda(novaComanda);
    }

    public ComandaDTO buscarComandaPorId(Long id) {
        return comandaService.buscarComandaPorId(id);
    }
}
